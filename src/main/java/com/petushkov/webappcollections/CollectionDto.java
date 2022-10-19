package com.petushkov.webappcollections;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Collection} entity
 */
@AllArgsConstructor
@Getter
public class CollectionDto implements Serializable {
    private final Long id;
    private final String name;
    private final String topic;
    private final String description;
    private final String link;
    private final String imgLink;
}