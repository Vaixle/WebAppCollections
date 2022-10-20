package com.petushkov.webappcollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ReadOnlyUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;

    private String username;
}
