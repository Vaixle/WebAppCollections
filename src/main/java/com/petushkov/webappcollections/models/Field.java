package com.petushkov.webappcollections.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "fields")
@Entity
@NoArgsConstructor
public class Field extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "name of field, that must be set for each created item", example = "count")
    private String name;
    @ApiModelProperty(notes = "type of collection field", example = "number")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "collection_id")
    @ToString.Exclude
    @ApiModelProperty(notes = "object of field owner, field owner is collection", example = "collection1")
    private Collection collection;

    public Field(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

