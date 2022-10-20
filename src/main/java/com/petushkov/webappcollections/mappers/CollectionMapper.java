package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.models.Collection;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {ItemMapper.class,
        ReadOnlyUserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CollectionMapper {
    Collection collectionDtoToCollection(CollectionDto collectionDto);

    CollectionDto collectionToCollectionDto(Collection collection);

    List<Collection> collectionDtosToCollections(List<CollectionDto> collectionDto);

    List<CollectionDto> collectionsToCollectionDtos(List<Collection> collection);

}
