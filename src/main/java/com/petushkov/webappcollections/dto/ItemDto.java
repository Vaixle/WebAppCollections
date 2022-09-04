package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Like;
import com.petushkov.webappcollections.models.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ItemDto {

    private String name;

    private List<Tag> tags;

    private List<Like> likes;
}
