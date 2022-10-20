package com.petushkov.webappcollections.controllers.rest;


//import com.petushkov.webappcollections.services.impl.FieldInitializeServiceImpl;
//import io.swagger.annotations.*;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Processing requests for creating item's fields
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/app-collections/fields-initialize")
@AllArgsConstructor
public class FieldInitializeRestController {

//    private FieldInitializeServiceImpl fieldInitializeService;
//
//
//    @ApiOperation(value = "Update value of Item field", notes = "Find field with id and update")
//    @PutMapping(value = "/{id}", params = {"field"})
//    public ResponseEntity<?> setItemFieldValue(
//            @PathVariable @ApiParam(name = "id", value = "id of item's field", example = "1", required = true) Long id,
//                                               @RequestBody ItemFieldValueDto itemFieldValueDto) {
//
//
//        return fieldInitializeService.setItemFieldValue(id, itemFieldValueDto);
//    }
}
