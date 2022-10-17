package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.dto.MessageResponseDto;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.mappers.UserDetailsMapper;
import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.models.impl.UserDetailsImpl;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private ChangeStyleServiceImpl changeStyleService;

    private RefreshTokenServiceImpl refreshTokenService;

    @Override
    public ResponseEntity<?> createUser(UserDetailsDto userDetailsDto) {


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
    public ResponseEntity<?> signOut(Long id ) {

        refreshTokenService.deleteAllByUserId(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public String getUserPage(String username, Model model, String style, Principal principal) {

        changeStyleService.changeStyle(model, style, principal);

        model.addAttribute("pageOwner", username);

        return "user";
    }
}
