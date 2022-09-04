package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.UserDetailsDto;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDto entityToDto(Collection collection);

    Collection DtoToEntity(CollectionDto collectionDto);

    List<CollectionDto> entitiesToDtos(List<Collection> collections);

    List<Collection> DtosToEntities(List<CollectionDto> collectionDtos);
}
