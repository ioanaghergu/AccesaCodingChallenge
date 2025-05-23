package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.PriceAlertDTO;
import org.market.pricecomparator.model.entity.PriceAlert;

@Mapper(componentModel = "spring")
public interface PriceAlertMapper {
    PriceAlertDTO toPriceAlertDTO(PriceAlert priceAlert);
    PriceAlert toPriceAlert(PriceAlertDTO priceAlertDTO);
}
