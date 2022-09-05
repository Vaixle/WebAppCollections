package com.petushkov.webappcollections.controllers.rest;


import com.petushkov.webappcollections.services.impl.CommentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * Processing requests for comments
 */
@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/app-collections/comments")
public class CommentRestController {

    private CommentServiceImpl commentService;

    @PostMapping(value = "/{id}", params = {"like"})
    @ApiOperation(value = "Add like", notes = "Add like for specific comment")
    public ResponseEntity<?> addCommentLike(
            @ApiParam(name = "id", value = "Id of specific comment", example = "1", required = true)
            @PathVariable Long id, Principal principal) {

        return commentService.addCommentLike(id, principal);
    }
}
