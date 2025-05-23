package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.PriceEntryDTO;
import org.market.pricecomparator.model.entity.PriceEntry;

@Mapper(componentModel = "spring")
public interface PriceEntryMapper {
    PriceEntryDTO toPriceEntryDTO(PriceEntry priceEntry);
    PriceEntry toPriceEntry(PriceEntryDTO priceEntryDTO);
}
