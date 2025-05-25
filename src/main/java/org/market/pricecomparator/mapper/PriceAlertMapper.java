package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.market.pricecomparator.dto.PriceAlertRequestDTO;
import org.market.pricecomparator.dto.PriceAlertResponseDTO;
import org.market.pricecomparator.model.entity.PriceAlert;

@Mapper(componentModel = "spring")
public interface PriceAlertMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.id", target = "userId")
    PriceAlertResponseDTO toPriceAlertResponseDTO(PriceAlert priceAlert);

    PriceAlertRequestDTO toPriceAlertRequestDTO(PriceAlertResponseDTO priceAlertResponseDTO);

}
