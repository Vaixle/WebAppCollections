package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.LikeDto;
import com.petushkov.webappcollections.models.Like;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LikeMapper {
    Like likeDtoToLike(LikeDto likeDto);

    LikeDto likeToLikeDto(Like like);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Like updateLikeFromLikeDto(LikeDto likeDto, @MappingTarget Like like);
}
