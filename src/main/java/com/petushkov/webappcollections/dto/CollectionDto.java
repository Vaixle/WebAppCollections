package com.petushkov.webappcollections.dto;

import com.petushkov.webappcollections.models.Field;
import com.petushkov.webappcollections.models.Item;
import lombok.Data;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@Data
public class CollectionDto {

    private String id;

    private String name;

    private String topic;

    private String description;

    private String link;

    private MultipartFile image;

    private String imgLink;

}
