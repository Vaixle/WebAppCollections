package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.FieldInitialize} entity
 */
@AllArgsConstructor
@Getter
public class FieldInitializeDto implements Serializable {
    private final Long id;
    private final Date createdAt;
    private final Date updatedAt;
    private final String name;
    private final String type;
    private final Long number;
    private final String text;
    private final String textarea;
    private final String logic;
    private final LocalDate date;
}