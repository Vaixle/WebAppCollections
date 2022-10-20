package com.petushkov.webappcollections.controllers.rest;

import com.petushkov.webappcollections.config.JwtUtils;
import com.petushkov.webappcollections.dto.UserDto;
import com.petushkov.webappcollections.exceptions.TokenRefreshException;
import com.petushkov.webappcollections.models.RefreshToken;
import com.petushkov.webappcollections.models.impl.UserDetailsImpl;
import com.petushkov.webappcollections.payload.response.JwtResponse;
import com.petushkov.webappcollections.payload.request.LoginRequest;
import com.petushkov.webappcollections.payload.request.TokenRefreshRequest;
import com.petushkov.webappcollections.payload.response.TokenRefreshResponse;
import com.petushkov.webappcollections.repositories.RoleRepository;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.UserService;
import com.petushkov.webappcollections.services.impl.RefreshTokenServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RefreshTokenServiceImpl refreshTokenService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {

        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }


    @ApiOperation(value = "Create user", notes = "Registering a specified user")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDetailsDto) {

        return userService.createUser(userDetailsDto);


    }

    @ApiOperation(value = "Sign out", notes = "Sign out")
    @PostMapping("/signout/{id}")
    public ResponseEntity<?> signOut(@PathVariable Long id) {

        return userService.signOut(id);
    }

}
