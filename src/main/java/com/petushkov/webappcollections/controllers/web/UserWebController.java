package com.petushkov.webappcollections.controllers.web;

import com.petushkov.webappcollections.services.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * Processing requests for user page
 */
@Controller
@RequestMapping
@AllArgsConstructor
@SessionAttributes(value = {"lang", "style"})
public class UserWebController {

    private UserServiceImpl userService;

    @GetMapping("/{username:^(?:(?!login|sign-up|sign_up_ru|favicon\\.ico|search|bad-credentials).)*$}")
    @ApiOperation(value = "User page", notes = "Get user page")
    public String getUserPage(
            @ApiParam(name = "username", value = "username of specific user", example = "ivan", required = true)
            @PathVariable String username,
            Model model,
            @ApiParam(name = "lang", value = "Language for content", example = "en")
            @RequestParam(required = false) String lang,
            @ApiParam(name = "style", value = "Style for content", example = "dark")
            @RequestParam(required = false) String style,
            Principal principal) {

        return userService.getUserPage(username, model, lang, style, principal);
    }

}