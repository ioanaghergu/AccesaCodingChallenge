package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.PriceAlertRequestDTO;
import org.market.pricecomparator.dto.PriceAlertResponseDTO;
import org.market.pricecomparator.model.entity.PriceAlert;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-25T11:55:07+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class PriceAlertMapperImpl implements PriceAlertMapper {

    @Override
    public PriceAlertResponseDTO toPriceAlertResponseDTO(PriceAlert priceAlert) {
        if ( priceAlert == null ) {
            return null;
        }

        PriceAlertResponseDTO priceAlertResponseDTO = new PriceAlertResponseDTO();

        priceAlertResponseDTO.setProductId( priceAlertProductId( priceAlert ) );
        priceAlertResponseDTO.setProductName( priceAlertProductName( priceAlert ) );
        priceAlertResponseDTO.setUserId( priceAlertUserId( priceAlert ) );
        priceAlertResponseDTO.setId( priceAlert.getId() );
        priceAlertResponseDTO.setTargetPrice( priceAlert.getTargetPrice() );
        priceAlertResponseDTO.setIsActive( priceAlert.getIsActive() );

        return priceAlertResponseDTO;
    }

    @Override
    public PriceAlertRequestDTO toPriceAlertRequestDTO(PriceAlertResponseDTO priceAlertResponseDTO) {
        if ( priceAlertResponseDTO == null ) {
            return null;
        }

        PriceAlertRequestDTO priceAlertRequestDTO = new PriceAlertRequestDTO();

        priceAlertRequestDTO.setUserId( priceAlertResponseDTO.getUserId() );
        priceAlertRequestDTO.setProductId( priceAlertResponseDTO.getProductId() );
        priceAlertRequestDTO.setTargetPrice( priceAlertResponseDTO.getTargetPrice() );

        return priceAlertRequestDTO;
    }

    private Long priceAlertProductId(PriceAlert priceAlert) {
        Product product = priceAlert.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String priceAlertProductName(PriceAlert priceAlert) {
        Product product = priceAlert.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }

    private Long priceAlertUserId(PriceAlert priceAlert) {
        User user = priceAlert.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
