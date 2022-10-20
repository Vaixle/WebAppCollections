package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.RoleDto;
import com.petushkov.webappcollections.models.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role updateRoleFromRoleDto(RoleDto roleDto, @MappingTarget Role role);
}
