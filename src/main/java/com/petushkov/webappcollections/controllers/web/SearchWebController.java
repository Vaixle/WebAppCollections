package com.petushkov.webappcollections.controllers.web;

import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.services.impl.HibernateSearchServiceImpl;
import com.petushkov.webappcollections.services.impl.SearchServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/search")
@AllArgsConstructor
public class SearchWebController {

    private SearchServiceImpl searchService;

    @GetMapping
    @ApiOperation(value = "Search page", notes = "Get search page")
    public String searchText(
            @ApiParam(name = "text", value = "search text", example = "toys", required = false)
            @RequestParam(required = false) String text,
            Model model,
            @ApiParam(name = "lang", value = "Language for content", example = "en")
            @RequestParam(required = false) String lang,
            @ApiParam(name = "style", value = "Style for content", example = "dark")
            @RequestParam(required = false) String style,
            Principal principal) {

        return searchService.searchText(text, model, lang, style,  principal);
    }
}
