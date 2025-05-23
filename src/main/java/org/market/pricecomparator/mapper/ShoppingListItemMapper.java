package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.ShoppingListItemDTO;
import org.market.pricecomparator.model.entity.ShoppingListItem;

@Mapper(componentModel = "spring")
public interface ShoppingListItemMapper {
    ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem);
    ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO);
}
