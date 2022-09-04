package com.petushkov.webappcollections.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

public interface CommentService {

    ResponseEntity<?> addCommentLike(@PathVariable Long id, Principal principal);
}
