package com.petushkov.webappcollections.dto;

import lombok.Data;

import java.util.List;

@Data
public class FieldsCreateDto {

    private List<String> number;

    private List<String> text;

    private List<String> textarea;

    private List<String> logic;

    private List<String> date;

}
