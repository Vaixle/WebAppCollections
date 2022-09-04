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

import java.util.List;

@Controller
@RequestMapping("/search")
@AllArgsConstructor
public class SearchWebController {

    private SearchServiceImpl searchService;

    @GetMapping
    @ApiOperation(value = "Search page", notes = "Get search page")
    public String searchText(
            @ApiParam(name = "text", value = "search text", example = "toys", required = true)
            @RequestParam String text,
            Model model) {

        return searchService.searchText(text, model);
    }
}
