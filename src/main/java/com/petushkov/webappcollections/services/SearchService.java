package com.petushkov.webappcollections.services;

import org.springframework.ui.Model;

public interface SearchService {

    String searchText(String text, Model model);
}
