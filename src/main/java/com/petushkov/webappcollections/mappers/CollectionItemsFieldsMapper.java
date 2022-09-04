package com.petushkov.webappcollections.mappers;


import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.CollectionFullinfDto;
import com.petushkov.webappcollections.models.Collection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionItemsFieldsMapper {

    CollectionFullinfDto entityToDto(Collection collection);

    Collection DtoToEntity(CollectionDto collectionDto);

    List<CollectionFullinfDto> entitiesToDtos(List<Collection> collections);

    List<Collection> DtosToEntities(List<CollectionDto> collectionDtos);
}
