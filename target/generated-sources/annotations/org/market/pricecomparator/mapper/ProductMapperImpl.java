package org.market.pricecomparator.mapper;

import javax.annotation.processing.Generated;
import org.market.pricecomparator.dto.ProductDTO;
import org.market.pricecomparator.model.entity.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-25T11:51:27+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setCategory( product.getCategory() );
        productDTO.setBrand( product.getBrand() );
        productDTO.setPackageQuantity( product.getPackageQuantity() );
        productDTO.setUnit( product.getUnit() );
        productDTO.setCurrency( product.getCurrency() );

        return productDTO;
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setCategory( productDTO.getCategory() );
        product.setBrand( productDTO.getBrand() );
        product.setPackageQuantity( productDTO.getPackageQuantity() );
        product.setUnit( productDTO.getUnit() );
        product.setCurrency( productDTO.getCurrency() );

        return product;
    }
}
