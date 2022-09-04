package com.petushkov.webappcollections.controllers.rest;

import com.petushkov.webappcollections.dto.CommentDto;
import com.petushkov.webappcollections.services.impl.CommentWebSocketServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;


@RestController
@AllArgsConstructor

public class CommentWebSocketRestController {

    private CommentWebSocketServiceImpl commentWebSocketService;


    @MessageMapping("/comments/{to}")
    @Transactional
    public void sendMessage(@DestinationVariable String to, CommentDto commentDto, Principal principal) {



        commentWebSocketService.sendMessage(to, commentDto, principal);

    }

}
