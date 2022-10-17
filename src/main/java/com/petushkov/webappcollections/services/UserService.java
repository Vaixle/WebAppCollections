package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.UserDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.security.Principal;

public interface UserService {

    ResponseEntity<?> createUser(UserDetailsDto userDetailsDto);

    String getUserPage(String username, Model model, String style, Principal principal);

    ResponseEntity<?> signOut(Long id );

}
