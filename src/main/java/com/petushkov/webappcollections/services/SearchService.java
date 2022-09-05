package com.petushkov.webappcollections.services;

import org.springframework.ui.Model;

import java.security.Principal;

public interface SearchService {

    String searchText(String text, Model model, String lang, String style,  Principal principal);
}
