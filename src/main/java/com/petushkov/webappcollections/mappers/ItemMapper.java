package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.ItemDto;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto entityToDto(Item item);

    Item DtoToEntity(ItemDto itemDto);

    List<ItemDto> entitiesToDtos(List<Item> items);

    List<Item> DtosToEntities(List<ItemDto> ItemDtos);
}
