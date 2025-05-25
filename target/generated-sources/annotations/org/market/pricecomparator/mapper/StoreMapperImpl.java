package org.market.pricecomparator.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.dto.StoreDTO;
import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T23:35:35+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDTO toStoreDTO(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setId( store.getId() );
        storeDTO.setName( store.getName() );
        storeDTO.setDiscounts( discountListToDiscountDTOList( store.getDiscounts() ) );

        return storeDTO;
    }

    @Override
    public Store toStore(StoreDTO storeDTO) {
        if ( storeDTO == null ) {
            return null;
        }

        Store store = new Store();

        store.setId( storeDTO.getId() );
        store.setName( storeDTO.getName() );
        store.setDiscounts( discountDTOListToDiscountList( storeDTO.getDiscounts() ) );

        return store;
    }

    protected DiscountDTO discountToDiscountDTO(Discount discount) {
        if ( discount == null ) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setId( discount.getId() );
        discountDTO.setPercentage( discount.getPercentage() );
        discountDTO.setStartDate( discount.getStartDate() );
        discountDTO.setEndDate( discount.getEndDate() );
        discountDTO.setEntryDate( discount.getEntryDate() );

        return discountDTO;
    }

    protected List<DiscountDTO> discountListToDiscountDTOList(List<Discount> list) {
        if ( list == null ) {
            return null;
        }

        List<DiscountDTO> list1 = new ArrayList<DiscountDTO>( list.size() );
        for ( Discount discount : list ) {
            list1.add( discountToDiscountDTO( discount ) );
        }

        return list1;
    }

    protected Discount discountDTOToDiscount(DiscountDTO discountDTO) {
        if ( discountDTO == null ) {
            return null;
        }

        Discount discount = new Discount();

        discount.setId( discountDTO.getId() );
        discount.setPercentage( discountDTO.getPercentage() );
        discount.setStartDate( discountDTO.getStartDate() );
        discount.setEndDate( discountDTO.getEndDate() );
        discount.setEntryDate( discountDTO.getEntryDate() );

        return discount;
    }

    protected List<Discount> discountDTOListToDiscountList(List<DiscountDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Discount> list1 = new ArrayList<Discount>( list.size() );
        for ( DiscountDTO discountDTO : list ) {
            list1.add( discountDTOToDiscount( discountDTO ) );
        }

        return list1;
    }
}
