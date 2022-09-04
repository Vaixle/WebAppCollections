package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.dto.CommentDto;
import com.petushkov.webappcollections.mappers.CommentMapper;
import com.petushkov.webappcollections.models.Comment;
import com.petushkov.webappcollections.models.Item;
import com.petushkov.webappcollections.repositories.CommentRepository;
import com.petushkov.webappcollections.repositories.ItemRepository;
import com.petushkov.webappcollections.services.CommentWebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class CommentWebSocketServiceImpl implements CommentWebSocketService {

    private SimpMessagingTemplate simpMessagingTemplate;

    private ItemRepository itemRepository;

    private CommentRepository commentRepository;

    private CommentMapper commentMapper;

    @Override
    @MessageMapping("/comments/{to}")
    public void sendMessage(@DestinationVariable String to, CommentDto commentDto, Principal principal) {

        commentDto.setFromUsername(principal.getName());
        commentDto.setToUsername(to);

        System.out.println("Handling message: " + commentDto + " to: " + to );

        Item item = itemRepository.findById(commentDto.getItemId()).get();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        commentDto.setCreatedAt(LocalDateTime.parse( commentDto.getCreatedAt(), formatter ).toString() );

        Comment comment = commentMapper.DtoToEntity(commentDto);

        comment.setItem(item);

        commentRepository.save(comment);

        comment = commentRepository.save(comment);

        commentRepository.flush();

        commentDto.setId(comment.getId());

        simpMessagingTemplate.convertAndSend("/topic/messages/" + to , commentDto);

    }
}
