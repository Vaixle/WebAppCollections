package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.CommentDto;

import java.security.Principal;

public interface CommentWebSocketService {

    void sendMessage(String to, CommentDto commentDto, Principal principal);
}
