package com.petushkov.webappcollections.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "likes")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Like extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "username who put like", example = "ivan")
    private String username;

    public Like(String username) {
        this.username = username;
    }

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "item_id")
    @ToString.Exclude
    @ApiModelProperty(notes = "the object of the like owner, like an owner can be an item", example = "item1")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    @ApiModelProperty(notes = "the object of the like owner, like an owner can be an comment", example = "comment1")
    private Comment comment;

}
