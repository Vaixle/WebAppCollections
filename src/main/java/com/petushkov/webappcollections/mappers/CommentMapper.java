package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CommentDto;
import com.petushkov.webappcollections.dto.ItemDto;
import com.petushkov.webappcollections.models.Comment;
import com.petushkov.webappcollections.models.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto entityToDto(Comment comment);

    Comment DtoToEntity(CommentDto commentDto);

    List<CommentDto> entitiesToDtos(List<Comment> comments);

    List<Comment> DtosToEntities(List<CommentDto> commentDtos);
}
