package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.FieldsCreateDto;
import com.petushkov.webappcollections.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

public interface CollectionService {

    ResponseEntity<?> createCollection(Optional<String> username, CollectionDto collectionDto);

    ResponseEntity<?> getMarkDownHTML(Long id, CollectionDto collectionDto);

    ResponseEntity<?> createItem(Long id, ItemDto itemDto);

    ResponseEntity<?> setCollectionFields(Long id, FieldsCreateDto fieldsCreateDto);

    ModelAndView findAllCollections(Optional<Long> id, Optional<String> username, Model model);

    ResponseEntity<?> deleteCollection(Long id);

    String getCollectionPage(String username, String collectionName, Model model,String lang, String style, Principal principal);

}
