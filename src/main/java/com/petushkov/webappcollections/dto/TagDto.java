package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Tag} entity
 */
@AllArgsConstructor
@Getter
public class TagDto implements Serializable {
    private final Long id;
    private final String name;
}