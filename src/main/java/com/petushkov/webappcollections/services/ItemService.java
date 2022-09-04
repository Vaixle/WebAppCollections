package com.petushkov.webappcollections.services;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.security.Principal;

public interface ItemService {

    ResponseEntity<?> deleteItem(Long id);

    String getItemPage(String username, Long id, Model model, String lang, String style, Principal principal);

    ResponseEntity<?> likeItem(Long id, Principal principal);

}
