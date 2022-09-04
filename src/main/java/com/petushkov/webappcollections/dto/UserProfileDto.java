package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserProfileDto {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String status;

    private Set<Role> roles;

}
