package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.ShoppingListItemDTO;
import org.market.pricecomparator.model.entity.ShoppingListItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T18:12:07+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ShoppingListItemMapperImpl implements ShoppingListItemMapper {

    @Override
    public ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem) {
        if ( shoppingListItem == null ) {
            return null;
        }

        ShoppingListItemDTO shoppingListItemDTO = new ShoppingListItemDTO();

        shoppingListItemDTO.setId( shoppingListItem.getId() );
        shoppingListItemDTO.setQuantity( shoppingListItem.getQuantity() );

        return shoppingListItemDTO;
    }

    @Override
    public ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO) {
        if ( shoppingListItemDTO == null ) {
            return null;
        }

        ShoppingListItem shoppingListItem = new ShoppingListItem();

        shoppingListItem.setId( shoppingListItemDTO.getId() );
        shoppingListItem.setQuantity( shoppingListItemDTO.getQuantity() );

        return shoppingListItem;
    }
}
