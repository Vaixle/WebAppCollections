package com.petushkov.webappcollections.controllers.web;

import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.dto.MessageResponseDto;
import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.mappers.UserDetailsMapper;
import com.petushkov.webappcollections.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign-up")
public class RegistrationWebController {

    @GetMapping
    @ApiOperation(value = "Registration page", notes = "Get registration page")
    public String registerForm() {

        return "registration";
    }

}