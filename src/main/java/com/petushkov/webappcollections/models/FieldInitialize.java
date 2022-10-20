package com.petushkov.webappcollections.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "fields_initialize")
public class FieldInitialize extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "name of field, set by the collection level", example = "count")
    private String name;

    @ApiModelProperty(notes = "name of field, set by the collection level", example = "number")
    private String type;

    @ApiModelProperty(notes = "value of field, set by the item level", example = "1")
    private Long number;

    @ApiModelProperty(notes = "value of field, set by the item level", example = "American rock band")
    private String text;

    @ApiModelProperty(notes = "value of field, set by the item level", example = "American rock band, founded in 1996")
    private String textarea;

    @ApiModelProperty(notes = "value of field, set by the item level", example = "yes/no")
    private String logic;

    @ApiModelProperty(notes = "value of field, set by the item level", example = "08/09/22 12:44:00")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "item_id")
    @ToString.Exclude
    @ApiModelProperty(notes = "object of comment owner, comment owner is item", example = "item1")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FieldInitialize that = (FieldInitialize) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
