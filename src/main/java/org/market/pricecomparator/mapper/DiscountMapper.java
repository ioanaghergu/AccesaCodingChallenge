package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.model.entity.Discount;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "store.id", target = "storeId")
    DiscountDTO toDiscountDTO(Discount discount);
    Discount toDiscount(DiscountDTO discountDTO);

}
