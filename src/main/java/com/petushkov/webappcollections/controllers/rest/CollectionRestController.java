package com.petushkov.webappcollections.controllers.rest;


import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.ItemDto;
import com.petushkov.webappcollections.services.impl.CollectionServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;


/**
 * Processing requests for collections
 */
@RestController
@RequestMapping("/api/collections")
@AllArgsConstructor
@SessionAttributes(value = {"lang", "style"})
public class CollectionRestController {

    private CollectionServiceImpl collectionService;


//    @PostMapping
//    @ApiOperation(value = "Create collection", notes = "Create collection for specific user")
//    public ResponseEntity<?> createCollection(
//            @ApiParam(name = "username", value = "name of collection owner", example = "ivan")
//            @RequestParam(required = false) Optional<String> username,
//            CollectionDto collectionDto)  {
//
//
//
//        return collectionService.createCollection(username, collectionDto);
//    }
//
//    @PostMapping("/add-description")
//    @ApiOperation(value = "Add description", notes = "Add markdown description for specific collection")
//    public ResponseEntity<?> getMarkDownHTML(
//            @ApiParam(name = "id", value = "collection id", example = "1", required = true)
//            @RequestParam Long id,
//            CollectionDto collectionDto) {
//
//        return collectionService.getMarkDownHTML(id, collectionDto);
//    }
//
//    @PostMapping(value = "/add-item")
//    @ApiOperation(value = "Create item", notes = "Create item for specific collection")
//    public ResponseEntity<?> createItem(
//            @ApiParam(name = "id", value = "collection id", example = "1", required = true)
//            @RequestParam Long id,
//            @RequestBody ItemDto itemDto) {
//
//        return collectionService.createItem(id, itemDto);
//    }

//    @PostMapping("/add-fields")
//    @ApiOperation(value = "Add fields", notes = "Add fields that must be set for each created item")
//    public ResponseEntity<?> setCollectionFields(
//            @ApiParam(name = "id", value = "collection id", example = "1", required = true)
//            @RequestParam Long id,
//            FieldsCreateDto fieldsCreateDto) {
//
//        return collectionService.setCollectionFields(id, fieldsCreateDto);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find collections", notes = "Find all collections by specific id or username or just all")
    public ModelAndView findAllCollections(
            @ApiParam(name = "id", value = "collection id", example = "1")
            @RequestParam(required = false) Optional<Long> id,
            @ApiParam(name = "username", value = "username of specific user", example = "ivan")
            @RequestParam(required = false) Optional<String> username,
            Model model
    ) {

        return collectionService.findAllCollections(id, username, model);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete collection", notes = "Delete collection by id")
    public ResponseEntity<?> deleteCollection(
            @ApiParam(name = "id", value = "collection id", example = "1")
            @PathVariable Long id) {

        return collectionService.deleteCollection(id);
    }

    @GetMapping("/top-collections")
    @ApiOperation(value = "Get top 5 collections", notes = "Get top 5 collections")
    public ResponseEntity<?> getTop5Collections() {

        return collectionService.getTop5Collections();
    }

}
