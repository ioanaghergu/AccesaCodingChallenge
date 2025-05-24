package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.ProductDTO;
import org.market.pricecomparator.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}
