package com.petushkov.webappcollections.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of comment, generated by DB", example = "1")
    private Long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Like like = (Like) o;
        return id != null && Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
