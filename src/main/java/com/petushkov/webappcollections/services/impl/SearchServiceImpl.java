package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private HibernateSearchServiceImpl hibernateSearchService;

    @Override
    public String searchText(String text, Model model) {

        List<Collection> collections = hibernateSearchService.search(text, 1000, 0);

        model.addAttribute("collections", collections);

        return "search";
    }
}
