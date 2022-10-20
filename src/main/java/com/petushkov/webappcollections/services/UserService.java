package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.security.Principal;

public interface UserService {

    ResponseEntity<?> createUser(UserDto userDetailsDto);

    String getUserPage(String username, Model model, String style, Principal principal);

    ResponseEntity<?> signOut(Long id );

}
