package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.TagDto;
import com.petushkov.webappcollections.models.Tag;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TagMapper {
    Tag tagDtoToTag(TagDto tagDto);

    TagDto tagToTagDto(Tag tag);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tag updateTagFromTagDto(TagDto tagDto, @MappingTarget Tag tag);
}
