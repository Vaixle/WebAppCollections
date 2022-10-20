package com.petushkov.webappcollections.controllers.web;

import com.petushkov.webappcollections.services.impl.CollectionServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Processing requests for collection's page
 */
@Controller
@AllArgsConstructor
@SessionAttributes(value = {"lang", "style"})
public class CollectionWebController {

    private CollectionServiceImpl collectionService;

//    @GetMapping("/{username:^(?:(?!login|sign-up|favicon\\.ico|js|css).)*$}/collections/{collectionName}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Collection page", notes = "Get collection page")
//    public String getCollectionPage(
//            @ApiParam(name = "username", value = "username of specific user", example = "ivan", required = true)
//            @PathVariable String username,
//            @ApiParam(name = "collectionName", value = "name of specific collection", example = "r2", required = true)
//            @PathVariable String collectionName,
//            Model model,
//            @ApiParam(name = "style", value = "Style for content", example = "dark")
//            @RequestParam(required = false) String style,
//            Principal principal) {
//
//        return collectionService.getCollectionPage(username, collectionName, model, style, principal);
//    }

}
