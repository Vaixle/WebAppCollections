package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.User} entity
 */
@AllArgsConstructor
@Getter
public class UserDto implements Serializable {
    private final Long id;
    private final Date createdAt;
    private final Date updatedAt;
    @NotBlank
    @Size(max = 20)
    private final String username;
    @NotBlank
    @Size(max = 120)
    private final String password;
    @NotBlank
    @Size(max = 50)
    private final String fullName;
    @NotBlank
    @Size(max = 50)
    @Email
    private final String email;
    private final String status;
    private final String language;
    private final String style;
}