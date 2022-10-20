package com.petushkov.webappcollections.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "message for specific item", example = "Cool item!")
    private String message;

    @ApiModelProperty(notes = "from user", example = "ivan")
    private String fromUsername;

    @ApiModelProperty(notes = "to user", example = "pavel")
    private String toUsername;

    public Comment(String message, String fromUsername, String toUsername) {
        this.message = message;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
    }

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = CascadeType.ALL)
    @ToString.Exclude
    @ApiModelProperty(notes = "set of item likes", example = "like1, like2, ...")
    private Set<Like> likes = new HashSet<>();

    public void addLike(Like like) {
        likes.add(like);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    @PrePersist
//    private void addCreatedAt() {
//        this.createdAt = LocalDateTime.now();
//    }
}
