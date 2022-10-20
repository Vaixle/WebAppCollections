package com.petushkov.webappcollections.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "items")
public class Item extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @FullTextField
    @ApiModelProperty(notes = "name of field, that must be set for each created item", example = "Linkin park")
    private String name;

    @ApiModelProperty(notes = "link of collection", example = "/ivan/collections/r2/items/2")
    private String link;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinTable(name = "item_tags",
    joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @IndexedEmbedded
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "collection_id")
    @ToString.Exclude
    @ApiModelProperty(notes = "object of item owner, collection owner is collection", example = "r2")
    private Collection collection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @ToString.Exclude
    @ApiModelProperty(notes = "set of item fields", example = "r2")
    private Set<FieldInitialize> fieldInitialize = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @ToString.Exclude
    @ApiModelProperty(notes = "set of item likes", example = "like1, like2, ...")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @ToString.Exclude
    @ApiModelProperty(notes = "set of item comments", example = "comment1, comment2, ...")
    private Set<Comment> comments = new HashSet<>();

    public void addLike(Like like) {
        likes.add(like);
    }

}
