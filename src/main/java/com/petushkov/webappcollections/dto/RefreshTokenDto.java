package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.petushkov.webappcollections.models.RefreshToken} entity
 */
@AllArgsConstructor
@Getter
public class RefreshTokenDto implements Serializable {
    private final Long id;
    private final String token;
    private final Instant expiryDate;
}