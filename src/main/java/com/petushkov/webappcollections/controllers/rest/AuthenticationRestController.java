package com.petushkov.webappcollections.controllers.rest;

import com.petushkov.webappcollections.dto.MessageResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Api(description = "Processing credentials errors")
public class AuthenticationRestController {


    @ApiOperation(value = "Send message if credentials is not valid", notes = "Get error message", response = MessageResponseDto.class)
    @GetMapping("/bad-credentials")
    public ResponseEntity<?> processAuthentication() {

        return new ResponseEntity(new MessageResponseDto("Bad credentials"),HttpStatus.UNAUTHORIZED);
    }
}
