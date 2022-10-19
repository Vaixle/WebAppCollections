package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.Like;
import com.petushkov.webappcollections.models.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ItemDto {

    private String id;

    private String name;

    private CollectionDto collection;

//    private List<Tag> tags;
//
//    private String link;
//
//    private List<Like> likes;
}
