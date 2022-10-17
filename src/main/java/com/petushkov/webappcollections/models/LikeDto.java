package com.petushkov.webappcollections.models;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Like} entity
 */
@Data
public class LikeDto implements Serializable {
    private final Long id;
    private final String username;
}