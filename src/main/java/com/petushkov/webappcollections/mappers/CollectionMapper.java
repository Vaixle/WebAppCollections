package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDto entityToDto(Collection collection);

    Collection DtoToEntity(CollectionDto collectionDto);

    List<CollectionDto> entitiesToDtos(List<Collection> collections);

    List<Collection> DtosToEntities(List<CollectionDto> collectionDtos);

    Collection collectionDtoToCollection(com.petushkov.webappcollections.CollectionDto collectionDto);

    com.petushkov.webappcollections.CollectionDto collectionToCollectionDto(Collection collection);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Collection updateCollectionFromCollectionDto(com.petushkov.webappcollections.CollectionDto collectionDto, @MappingTarget Collection collection);
}
