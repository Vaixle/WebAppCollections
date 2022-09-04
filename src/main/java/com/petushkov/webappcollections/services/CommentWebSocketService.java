package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.CommentDto;
import org.springframework.ui.Model;

import java.security.Principal;

public interface CommentWebSocketService {

    void sendMessage(String to, CommentDto commentDto, Principal principal);
}
