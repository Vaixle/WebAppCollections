package com.petushkov.webappcollections.controllers.rest;

import com.petushkov.webappcollections.services.impl.ItemServiceImpl;
import com.petushkov.webappcollections.services.impl.TagServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
