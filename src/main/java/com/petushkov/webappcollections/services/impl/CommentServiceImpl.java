package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.Comment;
import com.petushkov.webappcollections.models.Like;
import com.petushkov.webappcollections.repositories.CommentRepository;
import com.petushkov.webappcollections.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Override
    public ResponseEntity<?> addCommentLike(Long id, Principal principal) {

        Comment comment = commentRepository.findById(id).get();

        String username = principal.getName();

        List<String> usernames = comment.getLikes().stream().map(Like::getUsername).collect(Collectors.toList());

        if(!usernames.contains(username)) {
            Like like = new Like(username);
            like.setComment(comment);
            comment.addLike(like);
            commentRepository.save(comment);
        }

        return ResponseEntity.ok().build();
    }

}
