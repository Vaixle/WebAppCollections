package com.petushkov.webappcollections.controllers.rest;


import com.petushkov.webappcollections.services.impl.TagServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Processing requests for tags
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/app-collections/tags")
@AllArgsConstructor
public class TagRestController {

    private TagServiceImpl tagService;

    @GetMapping(params = {"names"})
    @ApiOperation(value = "Find tags", notes = "Find all tags")
    public Set<String> getTagNames() {

    return  tagService.getTagNames();
    }
}
