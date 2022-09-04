package com.petushkov.webappcollections.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;

    private String message;

    private String fromUsername;

    private String toUsername;

    private Long itemId;

    private String createdAt;

    private String language;
}
