package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.PriceEntryDTO;
import org.market.pricecomparator.model.entity.PriceEntry;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T22:21:33+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PriceEntryMapperImpl implements PriceEntryMapper {

    @Override
    public PriceEntryDTO toPriceEntryDTO(PriceEntry priceEntry) {
        if ( priceEntry == null ) {
            return null;
        }

        PriceEntryDTO priceEntryDTO = new PriceEntryDTO();

        priceEntryDTO.setId( priceEntry.getId() );
        priceEntryDTO.setPrice( priceEntry.getPrice() );
        priceEntryDTO.setEntryDate( priceEntry.getEntryDate() );

        return priceEntryDTO;
    }

    @Override
    public PriceEntry toPriceEntry(PriceEntryDTO priceEntryDTO) {
        if ( priceEntryDTO == null ) {
            return null;
        }

        PriceEntry priceEntry = new PriceEntry();

        priceEntry.setId( priceEntryDTO.getId() );
        priceEntry.setPrice( priceEntryDTO.getPrice() );
        priceEntry.setEntryDate( priceEntryDTO.getEntryDate() );

        return priceEntry;
    }
}
