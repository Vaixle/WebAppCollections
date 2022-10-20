package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.FieldInitializeDto;
import com.petushkov.webappcollections.models.FieldInitialize;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FieldInitializeMapper {
    FieldInitialize fieldInitializeDtoToFieldInitialize(FieldInitializeDto fieldInitializeDto);

    FieldInitializeDto fieldInitializeToFieldInitializeDto(FieldInitialize fieldInitialize);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FieldInitialize updateFieldInitializeFromFieldInitializeDto(FieldInitializeDto fieldInitializeDto, @MappingTarget FieldInitialize fieldInitialize);
}
