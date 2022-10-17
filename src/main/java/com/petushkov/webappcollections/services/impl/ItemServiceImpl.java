package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.dto.ItemFieldValueDto;
import com.petushkov.webappcollections.models.FieldInitialize;
import com.petushkov.webappcollections.models.Item;
import com.petushkov.webappcollections.models.Like;
import com.petushkov.webappcollections.repositories.ItemRepository;
import com.petushkov.webappcollections.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    private ChangeStyleServiceImpl changeStyleService;

    @Override
    public ResponseEntity<?> deleteItem(Long id) {
        itemRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public String getItemPage(String username, Long id, Model model, String style, Principal principal) {

        Item item = itemRepository.findById(id).get();

        changeStyleService.changeStyle(model, style, principal);

        List<FieldInitialize> fieldInitializeNumber = new ArrayList<>();
        List<FieldInitialize> fieldInitializeText = new ArrayList<>();
        List<FieldInitialize> fieldInitializeTextArea = new ArrayList<>();
        List<FieldInitialize> fieldInitializeLogic = new ArrayList<>();
        List<FieldInitialize> fieldInitializeDate = new ArrayList<>();

        for(FieldInitialize f : item.getFieldInitialize()) {

            switch (f.getType()){
                case "number": fieldInitializeNumber.add(f);
                    break;
                case "text": fieldInitializeText.add(f);
                    break;
                case "textarea": fieldInitializeTextArea.add(f);
                    break;
                case "logic": fieldInitializeLogic.add(f);
                    break;
                case "date": fieldInitializeDate.add(f);
                    break;
            }
        }

        Comparator<FieldInitialize> mapComparator = Comparator.comparing(FieldInitialize::getName);

        Collections.sort(fieldInitializeNumber, mapComparator);
        Collections.sort(fieldInitializeText, mapComparator);
        Collections.sort(fieldInitializeTextArea, mapComparator);
        Collections.sort(fieldInitializeLogic, mapComparator);
        Collections.sort(fieldInitializeDate, mapComparator);

        model.addAttribute("item", item);
        model.addAttribute("number", fieldInitializeNumber);
        model.addAttribute("text", fieldInitializeText);
        model.addAttribute("textarea", fieldInitializeTextArea);
        model.addAttribute("logic", fieldInitializeLogic);
        model.addAttribute("date", fieldInitializeDate);
        model.addAttribute("pageOwner", username);
        model.addAttribute("likes", item.getLikes().size());
        model.addAttribute("comments", item.getComments());

        return "item";
    }

    @Override
    public ResponseEntity<?> likeItem(Long id, Principal principal) {
        Item item = itemRepository.findById(id).get();

        String username = principal.getName();

        List<String> usernames = item.getLikes().stream().map(Like::getUsername).collect(Collectors.toList());

        if(!usernames.contains(username)) {
            Like like = new Like(username);
            like.setItem(item);
            item.addLike(like);
            itemRepository.save(item);
        }

        return ResponseEntity.ok().build();
    }


}
