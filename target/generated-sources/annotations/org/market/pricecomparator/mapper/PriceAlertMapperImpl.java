package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.PriceAlertDTO;
import org.market.pricecomparator.model.entity.PriceAlert;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T23:35:35+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class PriceAlertMapperImpl implements PriceAlertMapper {

    @Override
    public PriceAlertDTO toPriceAlertDTO(PriceAlert priceAlert) {
        if ( priceAlert == null ) {
            return null;
        }

        PriceAlertDTO priceAlertDTO = new PriceAlertDTO();

        priceAlertDTO.setId( priceAlert.getId() );
        priceAlertDTO.setTargetPrice( priceAlert.getTargetPrice() );
        priceAlertDTO.setIsActive( priceAlert.getIsActive() );

        return priceAlertDTO;
    }

    @Override
    public PriceAlert toPriceAlert(PriceAlertDTO priceAlertDTO) {
        if ( priceAlertDTO == null ) {
            return null;
        }

        PriceAlert priceAlert = new PriceAlert();

        priceAlert.setId( priceAlertDTO.getId() );
        priceAlert.setTargetPrice( priceAlertDTO.getTargetPrice() );
        priceAlert.setIsActive( priceAlertDTO.getIsActive() );

        return priceAlert;
    }
}
