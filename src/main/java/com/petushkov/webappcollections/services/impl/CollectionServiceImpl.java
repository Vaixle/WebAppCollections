package com.petushkov.webappcollections.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.CollectionFullinfDto;
import com.petushkov.webappcollections.dto.FieldsCreateDto;
import com.petushkov.webappcollections.dto.ItemDto;
import com.petushkov.webappcollections.mappers.CollectionItemsFieldsMapper;
import com.petushkov.webappcollections.mappers.CollectionMapper;
import com.petushkov.webappcollections.mappers.ItemMapper;
import com.petushkov.webappcollections.models.*;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.repositories.CollectionRepository;
import com.petushkov.webappcollections.repositories.ItemRepository;
import com.petushkov.webappcollections.repositories.TagRepository;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.CollectionService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service with functions to processing collection requests
 */
@Service
public class CollectionServiceImpl implements CollectionService {


    private CollectionRepository collectionRepository;

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private TagRepository tagRepository;

    private ChangeStyleServiceImpl changeStyleService;

    private CollectionMapper collectionMapper;

    private ItemMapper itemMapper;

    private CollectionItemsFieldsMapper collectionItemsFieldsMapper;

    @Value("${cloudinary.api}")
    private String cloudinaryApi;


    public CollectionServiceImpl(CollectionRepository collectionRepository, UserRepository userRepository, ItemRepository itemRepository, TagRepository tagRepository, ChangeStyleServiceImpl changeStyleService, CollectionMapper collectionMapper, ItemMapper itemMapper, CollectionItemsFieldsMapper collectionItemsFieldsMapper) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.tagRepository = tagRepository;
        this.changeStyleService = changeStyleService;
        this.collectionMapper = collectionMapper;
        this.itemMapper = itemMapper;
        this.collectionItemsFieldsMapper = collectionItemsFieldsMapper;
    }

    @Override
    public ResponseEntity<?> createCollection(Optional<String> username, CollectionDto collectionDto) {


        if (username.isPresent()) {

            if (collectionRepository.existsByNameAndUserUsername(collectionDto.getName(), username.get()))
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            Optional<User> optionalUser = userRepository.findByUsername(username.get());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                Collection collection = collectionMapper.DtoToEntity(collectionDto);

                collection.setUser(user);
                collection.setLink("/" + username.get() + "/collections/" + collection.getName());

                try {
                    collection = saveImg(collectionDto.getImage(), collection);
                } catch (IOException exception) {

                }

                collectionRepository.save(collection);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<?> getMarkDownHTML(Long id, CollectionDto collectionDto) {

        Collection collection = collectionRepository.findById(id).get();

        collection.setDescription(collectionDto.getDescription());
        collectionRepository.save(collection);
        collectionDto.setDescription(markdownToHTML(collectionDto.getDescription()));

        return new ResponseEntity<>(collectionDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTop5Collections() {
        return ResponseEntity
                .ok()
                .body(collectionMapper.entitiesToDtos(collectionRepository.findTop5CollectionOrderByItemsDesc()));
    }

    @Override
    public ResponseEntity<?> createItem(Long id, ItemDto itemDto) {
        Optional<Collection> collectionOptional = collectionRepository.findById(id);

        if (collectionOptional.isPresent()) {

            Collection collection = collectionOptional.get();
            Item item = itemMapper.DtoToEntity(itemDto);
            Set<Field> fields = collection.getFields();
            Set<FieldInitialize> fieldsInitialize = new HashSet<>();

            fields.forEach(f -> fieldsInitialize.add(createFieldInitialize(f)));

            for (FieldInitialize f : fieldsInitialize) {
                f.setItem(item);
            }

            Set<Tag> checkedTags = new HashSet<>();
            for (Tag t : item.getTags()) {
                Optional<Tag> tag = tagRepository.findByName(t.getName());
                if (tag.isPresent()) {
                    checkedTags.add(tag.get());
                }
                checkedTags.add(t);
            }

            item.setFieldInitialize(fieldsInitialize);
            item.setCollection(collection);
            item.setTags(checkedTags);
            collection.addItem(item);

            collectionRepository.save(collection);
            item = itemRepository.findFirstByCollectionIdOrderByIdDesc(collection.getId());
            item.setLink(collection.getLink() + "/items/" + item.getId());
            itemRepository.save(item);

            return ResponseEntity.ok().body(item.getId());
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> setCollectionFields(Long id, FieldsCreateDto fieldsCreateDto) {
        Optional<Collection> collectionOpt = collectionRepository.findById(id);

        if (collectionOpt.isPresent()) {
            Collection collection = collectionOpt.get();

            List<Field> fieldsNumber = fieldsCreateDto.getNumber().stream()
                    .filter(f -> !f.isEmpty())
                    .map(f -> new Field(f, "number"))
                    .collect(Collectors.toList());

            List<Field> fieldsText = fieldsCreateDto.getText().stream()
                    .filter(f -> !f.isEmpty())
                    .map(f -> new Field(f, "text"))
                    .collect(Collectors.toList());

            List<Field> fieldsTextarea = fieldsCreateDto.getTextarea().stream()
                    .filter(f -> !f.isEmpty())
                    .map(f -> new Field(f, "textarea"))
                    .collect(Collectors.toList());

            List<Field> fieldsLogic = fieldsCreateDto.getLogic().stream()
                    .filter(f -> !f.isEmpty())
                    .map(f -> new Field(f, "logic"))
                    .collect(Collectors.toList());

            List<Field> fieldsDate = fieldsCreateDto.getDate().stream()
                    .filter(f -> !f.isEmpty())
                    .map(f -> new Field(f, "date"))
                    .collect(Collectors.toList());

            List<Field> fields = new ArrayList<>();

            fields.addAll(fieldsNumber);
            fields.addAll(fieldsText);
            fields.addAll(fieldsTextarea);
            fields.addAll(fieldsLogic);
            fields.addAll(fieldsDate);

            fields.forEach(f -> f.setCollection(collection));

            collection.setFields(new HashSet<>(fields));

            collectionRepository.save(collection);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ModelAndView findAllCollections(Optional<Long> id, Optional<String> username, Model model) {
        List<Collection> collections = new ArrayList<>();

        ModelAndView mv;

        if (id.isPresent()) {
            collections = collectionRepository.findAllByUserId(id.get());
        }

        if (username.isPresent()) {
            collections = collectionRepository.findAllByUserUsername(username.get());
        }

        if (!id.isPresent() && !username.isPresent()) {
            collections = collectionRepository.findAll();
        }

        List<CollectionDto> collectionDtos = collectionMapper.entitiesToDtos(collections);


        mv = new ModelAndView("user::collections");


        mv.addObject("collections", collectionDtos);
        mv.addObject("username", username.get());
        mv.addObject("pageOwner", username.get());

        return mv;
    }

    @Override
    public ResponseEntity<?> deleteCollection(Long id) {
        collectionRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public String getCollectionPage(String username, String collectionName, Model model, String style, Principal principal) {

        changeStyleService.changeStyle(model, style, principal);


        Collection collection = collectionRepository.findByNameAndUserUsername(collectionName, username);
        CollectionFullinfDto collectionFullinfDto = collectionItemsFieldsMapper.entityToDto(collection);

        if (collectionFullinfDto.getDescription() != null) {
            collectionFullinfDto.setDescription(markdownToHTML(collectionFullinfDto.getDescription()));

        }


        model.addAttribute("collection::description", collectionFullinfDto.getDescription());



        declareFields(collectionFullinfDto, model);

        model.addAttribute("pageOwner", username);


        return "collection";
    }


    private String markdownToHTML(String markdown) {
        Parser parser = Parser.builder()
                .build();

        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();

        return renderer.render(document);
    }

    private FieldInitialize createFieldInitialize(Field field) {

        FieldInitialize fieldInitialize = new FieldInitialize();

        fieldInitialize.setName(field.getName());
        fieldInitialize.setType(field.getType());

        return fieldInitialize;

    }

    private void declareFields(CollectionFullinfDto collection, Model model) {
        List<Field> fieldNumber = new ArrayList<>();
        List<Field> fieldText = new ArrayList<>();
        List<Field> fieldTextArea = new ArrayList<>();
        List<Field> fieldLogic = new ArrayList<>();
        List<Field> fieldDate = new ArrayList<>();

        for (Field f : collection.getFields()) {

            switch (f.getType()) {
                case "number":
                    fieldNumber.add(f);
                    break;
                case "text":
                    fieldText.add(f);
                    break;
                case "textarea":
                    fieldTextArea.add(f);
                    break;
                case "logic":
                    fieldLogic.add(f);
                    break;
                case "date":
                    fieldDate.add(f);
                    break;
            }
        }

        model.addAttribute("collection", collection);
        model.addAttribute("number", fieldNumber);
        model.addAttribute("text", fieldText);
        model.addAttribute("textarea", fieldTextArea);
        model.addAttribute("logic", fieldLogic);
        model.addAttribute("date", fieldDate);
    }

    private Collection saveImg(MultipartFile multfile, Collection collection) throws IOException {

        String fileName = multfile.getOriginalFilename();

        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = File.createTempFile(fileName, prefix);

        multfile.transferTo(file);

        Cloudinary cloudinary = new Cloudinary(cloudinaryApi);

        String imgLink = cloudinary.uploader().upload(file, ObjectUtils.emptyMap()).get("url").toString();

        collection.setImgLink(imgLink);

        deleteFile(file);

        return collection;
    }


    private void deleteFile(File file) {

        if (file.exists()) {
            file.delete();
        }

    }



}