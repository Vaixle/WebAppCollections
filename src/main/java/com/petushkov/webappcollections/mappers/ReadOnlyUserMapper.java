package com.petushkov.webappcollections.mappers;


import com.petushkov.webappcollections.dto.ReadOnlyUserDto;
import com.petushkov.webappcollections.dto.UserDto;
import com.petushkov.webappcollections.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReadOnlyUserMapper {

    ReadOnlyUserDto userToUserDto(User user);

    List<ReadOnlyUserDto> usersToUserDtos(List<User> users);
}
