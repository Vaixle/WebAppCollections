package com.petushkov.webappcollections.payload.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

     String token;

    String refreshToken;

    String tokenType = "Bearer";
    Long id;
    String username;
    List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
