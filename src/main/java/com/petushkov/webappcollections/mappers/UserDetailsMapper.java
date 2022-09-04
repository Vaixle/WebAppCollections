package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsDto entityToDto(User user);

    User DtoToEntity(UserDetailsDto detailsDto);
}
