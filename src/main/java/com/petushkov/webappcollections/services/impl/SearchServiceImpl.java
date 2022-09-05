package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private HibernateSearchServiceImpl hibernateSearchService;

    private  ChangeLanguageServiceImpl changeLanguage;

    private ChangeStyleServiceImpl changeStyleService;

    @Override
    public String searchText(String text, Model model, String lang, String style,  Principal principal) {

        lang = changeLanguage.changeLanguage(model, lang, principal);

        changeStyleService.changeStyle(model, style, principal);

        List<Collection> collections = hibernateSearchService.search(text, 1000, 0);

        model.addAttribute("collections", collections);

        return lang != null && lang.equals("ru") ? "search_ru": "search";
    }
}
