package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.mappers.CollectionMapper;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.Item;
import com.petushkov.webappcollections.models.Tag;
import com.petushkov.webappcollections.repositories.CollectionRepository;
import com.petushkov.webappcollections.repositories.ItemRepository;
import com.petushkov.webappcollections.repositories.TagRepository;
import com.petushkov.webappcollections.services.MainPageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private CollectionRepository collectionRepository;

    private ItemRepository itemRepository;

    private CollectionMapper collectionMapper;

    private TagRepository tagRepository;

    private ChangeStyleServiceImpl changeStyleService;


    @Override
    public String getMainPage(Model model,  Principal principal) {


        List<Collection> collections = collectionRepository.findTop5CollectionOrderByItemsDesc();

        model.addAttribute("collections", collectionMapper.collectionsToCollectionDtos(collections));

        List<Item> items = itemRepository.findTop5ByOrderByCreatedAtDesc();

        model.addAttribute("items", items);

        List<Tag> tags = tagRepository.findAll();

        model.addAttribute("tags", tags);

        return "main";
    }
}
