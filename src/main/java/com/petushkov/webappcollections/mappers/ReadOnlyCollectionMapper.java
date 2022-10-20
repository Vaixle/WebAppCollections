package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.ReadOnlyCollectionDto;
import com.petushkov.webappcollections.models.Collection;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {ReadOnlyUserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ReadOnlyCollectionMapper {

    Collection ReadOnlyCollectionDtoToCollection(ReadOnlyCollectionDto readOnlyCollectionDto);

    ReadOnlyCollectionDto collectionToReadOnlyCollectionDto(Collection collection);
}
