package com.petushkov.webappcollections.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id generated by DB", example = "1")
    Long id;

    @CreationTimestamp
    @ApiModelProperty(notes = "Creation date", example = "08/09/22 12:44:00")
    Date createdAt;

//    @UpdateTimestamp
//    @ApiModelProperty(notes = "Update date", example = "08/09/22 12:44:00")
//    Date updatedAt;

}