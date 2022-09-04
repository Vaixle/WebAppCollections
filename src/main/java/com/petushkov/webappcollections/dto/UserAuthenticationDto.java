package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserAuthenticationDto {

    private String username;

    private String password;

    private Set<Role> roles;
}
