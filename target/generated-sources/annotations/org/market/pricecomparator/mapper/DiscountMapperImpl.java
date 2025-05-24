package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T18:43:43+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class DiscountMapperImpl implements DiscountMapper {

    @Override
    public DiscountDTO toDiscountDTO(Discount discount) {
        if ( discount == null ) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setProductId( discountProductId( discount ) );
        discountDTO.setStoreId( discountStoreId( discount ) );
        discountDTO.setId( discount.getId() );
        discountDTO.setPercentage( discount.getPercentage() );
        discountDTO.setStartDate( discount.getStartDate() );
        discountDTO.setEndDate( discount.getEndDate() );

        return discountDTO;
    }

    @Override
    public Discount toDiscount(DiscountDTO discountDTO) {
        if ( discountDTO == null ) {
            return null;
        }

        Discount discount = new Discount();

        discount.setId( discountDTO.getId() );
        discount.setPercentage( discountDTO.getPercentage() );
        discount.setStartDate( discountDTO.getStartDate() );
        discount.setEndDate( discountDTO.getEndDate() );

        return discount;
    }

    private Long discountProductId(Discount discount) {
        Product product = discount.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private Long discountStoreId(Discount discount) {
        Store store = discount.getStore();
        if ( store == null ) {
            return null;
        }
        return store.getId();
    }
}
