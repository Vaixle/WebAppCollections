package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Role} entity
 */
@AllArgsConstructor
@Getter
public class RoleDto implements Serializable {
    private final Long id;
    private final ERole name;
}