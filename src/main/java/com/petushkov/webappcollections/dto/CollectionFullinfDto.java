package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Field;
import com.petushkov.webappcollections.models.Item;
import lombok.Data;

import java.util.List;


@Data
public class CollectionFullinfDto extends CollectionDto {

    private List<Item> items;

    private List<Field> fields;
}
