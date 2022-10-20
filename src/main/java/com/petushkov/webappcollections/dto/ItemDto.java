package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Item} entity
 */
@AllArgsConstructor
@Getter
public class ItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Date createdAt;
//    private final Date updatedAt;
    private final String name;
    private final String link;
    private final ReadOnlyCollectionDto collection;
}