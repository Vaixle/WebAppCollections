package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.dto.MessageResponseDto;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.mappers.UserDetailsMapper;
import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private UserDetailsMapper userDetailsMapper;

    private ChangeLanguageServiceImpl changeLanguageService;

    ChangeStyleServiceImpl changeStyleService;

    @Override
    public ResponseEntity<?> createUser(UserDetailsDto userDetailsDto, Errors errors) {

        if(errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getAllErrors());

        if (userRepository.existsByUsername(userDetailsDto.getUsername()))
            return ResponseEntity.badRequest().body(new MessageResponseDto("Username is already taken!"));


        if (userRepository.existsByEmail(userDetailsDto.getEmail()))
            return ResponseEntity.badRequest().body(new MessageResponseDto("Email is already in use!"));


        User user = userDetailsMapper.DtoToEntity(userDetailsDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(new Role(ERole.ROLE_USER));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public String getUserPage(String username, Model model, String lang, String style, Principal principal) {

        lang = changeLanguageService.changeLanguage(model, lang, principal);

        changeStyleService.changeStyle(model, style, principal);

        model.addAttribute("pageOwner", username);

        return lang != null && lang.equals("ru") ? "user_ru": "user";
    }
}
