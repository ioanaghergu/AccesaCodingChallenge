package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(ProductMapper product);
    ProductMapper toProduct(ProductDTO productDTO);
}
