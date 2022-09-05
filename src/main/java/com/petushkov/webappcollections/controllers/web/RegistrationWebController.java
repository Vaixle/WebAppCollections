package com.petushkov.webappcollections.controllers.web;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Processing requests for registration page
 */
@Controller
@RequestMapping("/sign-up")
public class RegistrationWebController {

    @GetMapping
    @ApiOperation(value = "Registration page", notes = "Get registration page")
    public String registerForm() {

        return "registration";
    }

}