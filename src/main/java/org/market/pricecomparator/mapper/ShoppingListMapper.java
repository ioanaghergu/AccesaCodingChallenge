package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.ShoppingListDTO;
import org.market.pricecomparator.model.entity.ShoppingList;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {
    ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList);
    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);
}
