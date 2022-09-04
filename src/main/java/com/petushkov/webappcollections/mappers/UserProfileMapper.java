package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.UserProfileDto;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfileDto entityToDto(User user);

    User DtoToEntity(UserProfileDto userProfileDto);

    List<UserProfileDto> entitiesToDtos(List<User> users);

    List<User> DtosToEntities(List<UserProfileDto> userProfileDtos);
}
