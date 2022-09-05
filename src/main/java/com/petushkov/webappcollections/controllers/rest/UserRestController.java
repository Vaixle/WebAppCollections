package com.petushkov.webappcollections.controllers.rest;

import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.services.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Processing requests for users
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/app-collections/users")
@AllArgsConstructor
public class UserRestController {

    private UserServiceImpl userService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create user", notes = "Registering a specified user")
    public ResponseEntity<?> createUser(@Valid UserDetailsDto userDetailsDto, Errors errors) {

        return userService.createUser(userDetailsDto, errors);
    }



}
