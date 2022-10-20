package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.CollectionDto;
import com.petushkov.webappcollections.dto.ItemDto;
import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.Item;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {ReadOnlyCollectionMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {
    Item itemDtoToItem(ItemDto itemDto);

    ItemDto itemToItemDto(Item item);

    List<Item> itemDtosToItems(List<ItemDto> ItemDtos);

    List<ItemDto> itemsToItemDtos(List<Item> items);


}
