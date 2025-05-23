package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.model.entity.Discount;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    DiscountDTO toDiscountDTO(Discount discount);
    Discount toDiscount(DiscountDTO discountDTO);
}
