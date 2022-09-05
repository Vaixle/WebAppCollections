package com.petushkov.webappcollections.controllers.web;


import com.petushkov.webappcollections.services.impl.MainPageServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * Processing requests for main page
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
@SessionAttributes(value = {"lang", "style"})
public class MainPageWebController {

    private MainPageServiceImpl mainPageService;

    @GetMapping
    @ApiOperation(value = "Main page", notes = "Get main page")
    public String getMainPage(
            Model model,
            @ApiParam(name = "lang", value = "Language for content", example = "en")
            @RequestParam(required = false) String lang,
            @ApiParam(name = "style", value = "Style for content", example = "dark")
            @RequestParam(required = false) String style,
            Principal principal) {

        return mainPageService.getMainPage(model, lang, style, principal);
    }
}
