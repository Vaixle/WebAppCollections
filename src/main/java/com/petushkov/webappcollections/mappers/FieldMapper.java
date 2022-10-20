package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.FieldDto;
import com.petushkov.webappcollections.models.Field;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FieldMapper {
    Field fieldDtoToField(FieldDto fieldDto);

    FieldDto fieldToFieldDto(Field field);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Field updateFieldFromFieldDto(FieldDto fieldDto, @MappingTarget Field field);
}
