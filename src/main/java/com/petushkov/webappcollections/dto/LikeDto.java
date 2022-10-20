package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Like} entity
 */
@AllArgsConstructor
@Getter
public class LikeDto implements Serializable {
    private final Long id;
    private final Date createdAt;
    private final Date updatedAt;
    private final String username;
}