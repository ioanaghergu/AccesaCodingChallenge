package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.BasketDTO;
import org.market.pricecomparator.model.entity.Basket;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    BasketDTO toBasketDTO(Basket basket);
    Basket toBasket(BasketDTO basketDTO);
}
