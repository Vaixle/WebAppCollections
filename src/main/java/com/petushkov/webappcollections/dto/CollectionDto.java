package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.Collection} entity
 */
@AllArgsConstructor
@Getter
public class CollectionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
//    private final Date createdAt;
//    private final Date updatedAt;
    private final String name;
    private final String topic;
    private final String description;
    private final String link;
    private final String imgLink;
    private final ReadOnlyUserDto user;
    private final List<ItemDto> items;
}