package org.market.pricecomparator.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.BasketDTO;
import org.market.pricecomparator.dto.ShoppingListDTO;
import org.market.pricecomparator.dto.ShoppingListItemDTO;
import org.market.pricecomparator.model.entity.Basket;
import org.market.pricecomparator.model.entity.ShoppingList;
import org.market.pricecomparator.model.entity.ShoppingListItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T22:21:34+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class BasketMapperImpl implements BasketMapper {

    @Override
    public BasketDTO toBasketDTO(Basket basket) {
        if ( basket == null ) {
            return null;
        }

        BasketDTO basketDTO = new BasketDTO();

        basketDTO.setId( basket.getId() );
        basketDTO.setShoppingLists( shoppingListListToShoppingListDTOList( basket.getShoppingLists() ) );

        return basketDTO;
    }

    @Override
    public Basket toBasket(BasketDTO basketDTO) {
        if ( basketDTO == null ) {
            return null;
        }

        Basket basket = new Basket();

        basket.setId( basketDTO.getId() );
        basket.setShoppingLists( shoppingListDTOListToShoppingListList( basketDTO.getShoppingLists() ) );

        return basket;
    }

    protected ShoppingListItemDTO shoppingListItemToShoppingListItemDTO(ShoppingListItem shoppingListItem) {
        if ( shoppingListItem == null ) {
            return null;
        }

        ShoppingListItemDTO shoppingListItemDTO = new ShoppingListItemDTO();

        shoppingListItemDTO.setId( shoppingListItem.getId() );
        shoppingListItemDTO.setQuantity( shoppingListItem.getQuantity() );

        return shoppingListItemDTO;
    }

    protected List<ShoppingListItemDTO> shoppingListItemListToShoppingListItemDTOList(List<ShoppingListItem> list) {
        if ( list == null ) {
            return null;
        }

        List<ShoppingListItemDTO> list1 = new ArrayList<ShoppingListItemDTO>( list.size() );
        for ( ShoppingListItem shoppingListItem : list ) {
            list1.add( shoppingListItemToShoppingListItemDTO( shoppingListItem ) );
        }

        return list1;
    }

    protected ShoppingListDTO shoppingListToShoppingListDTO(ShoppingList shoppingList) {
        if ( shoppingList == null ) {
            return null;
        }

        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();

        shoppingListDTO.setId( shoppingList.getId() );
        shoppingListDTO.setTitle( shoppingList.getTitle() );
        shoppingListDTO.setItems( shoppingListItemListToShoppingListItemDTOList( shoppingList.getItems() ) );

        return shoppingListDTO;
    }

    protected List<ShoppingListDTO> shoppingListListToShoppingListDTOList(List<ShoppingList> list) {
        if ( list == null ) {
            return null;
        }

        List<ShoppingListDTO> list1 = new ArrayList<ShoppingListDTO>( list.size() );
        for ( ShoppingList shoppingList : list ) {
            list1.add( shoppingListToShoppingListDTO( shoppingList ) );
        }

        return list1;
    }

    protected ShoppingListItem shoppingListItemDTOToShoppingListItem(ShoppingListItemDTO shoppingListItemDTO) {
        if ( shoppingListItemDTO == null ) {
            return null;
        }

        ShoppingListItem shoppingListItem = new ShoppingListItem();

        shoppingListItem.setId( shoppingListItemDTO.getId() );
        shoppingListItem.setQuantity( shoppingListItemDTO.getQuantity() );

        return shoppingListItem;
    }

    protected List<ShoppingListItem> shoppingListItemDTOListToShoppingListItemList(List<ShoppingListItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ShoppingListItem> list1 = new ArrayList<ShoppingListItem>( list.size() );
        for ( ShoppingListItemDTO shoppingListItemDTO : list ) {
            list1.add( shoppingListItemDTOToShoppingListItem( shoppingListItemDTO ) );
        }

        return list1;
    }

    protected ShoppingList shoppingListDTOToShoppingList(ShoppingListDTO shoppingListDTO) {
        if ( shoppingListDTO == null ) {
            return null;
        }

        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setId( shoppingListDTO.getId() );
        shoppingList.setTitle( shoppingListDTO.getTitle() );
        shoppingList.setItems( shoppingListItemDTOListToShoppingListItemList( shoppingListDTO.getItems() ) );

        return shoppingList;
    }

    protected List<ShoppingList> shoppingListDTOListToShoppingListList(List<ShoppingListDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ShoppingList> list1 = new ArrayList<ShoppingList>( list.size() );
        for ( ShoppingListDTO shoppingListDTO : list ) {
            list1.add( shoppingListDTOToShoppingList( shoppingListDTO ) );
        }

        return list1;
    }
}
