package com.petushkov.webappcollections.controllers.web;


import com.petushkov.webappcollections.services.impl.ItemServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Processing requests for item's page
 */
@Controller
@AllArgsConstructor
@SessionAttributes(value = {"lang", "style"})
public class ItemWebController {

    private ItemServiceImpl itemService;

    @GetMapping("/{username:^(?:(?!login|sign-up|favicon\\.ico|js|css).)*$}/collections/{collectionName}/items/{id}")
    @ApiOperation(value = "Item page", notes = "Get item page")
    public String getItemPage(
            @ApiParam(name = "username", value = "username of specific user", example = "ivan", required = true)
            @PathVariable String username,
            @ApiParam(name = "id", value = "id of specific user", example = "1", required = true)
            @PathVariable Long id,
            Model model,
            @ApiParam(name = "style", value = "Style for content", example = "dark")
            @RequestParam(required = false) String style,
            Principal principal) {

        return itemService.getItemPage(username, id, model, style, principal);

    }


}