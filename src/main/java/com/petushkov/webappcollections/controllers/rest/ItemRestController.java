package com.petushkov.webappcollections.controllers.rest;


import com.petushkov.webappcollections.services.impl.ItemServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Processing requests for items
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/app-collections/items")
@AllArgsConstructor
public class ItemRestController {

    private ItemServiceImpl itemService;

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete item", notes = "Delete specific item by id")
    public ResponseEntity<?> deleteItem(
            @ApiParam(name = "id", value = "id of specific item", example = "1", required = true)
            @PathVariable Long id) {

        return itemService.deleteItem(id);
    }

    @PostMapping(value = "/{id}", params = {"like"})
    @ApiOperation(value = "Like item", notes = "Like specific item by id")
    public ResponseEntity<?> likeItem(
            @ApiParam(name = "id", value = "id of specific item", example = "1", required = true)
            @PathVariable Long id,
            Principal principal) {

        return itemService.likeItem(id, principal);
    }
}
