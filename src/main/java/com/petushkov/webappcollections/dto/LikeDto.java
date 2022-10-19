package com.petushkov.webappcollections.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Like} entity
 */
@Data
public class LikeDto implements Serializable {
    private final Long id;
    private final String username;
}