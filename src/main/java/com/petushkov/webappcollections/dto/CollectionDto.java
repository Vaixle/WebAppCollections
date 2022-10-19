package com.petushkov.webappcollections.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
