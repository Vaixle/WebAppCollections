package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Comment} entity
 */
@AllArgsConstructor
@Getter
public class CommentDto implements Serializable {
    private final Long id;
    private final Date createdAt;
    private final Date updatedAt;
    private final String message;
    private final String fromUsername;
    private final String toUsername;
}