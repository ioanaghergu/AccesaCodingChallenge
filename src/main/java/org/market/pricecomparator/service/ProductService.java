package org.market.pricecomparator.service;


import org.market.pricecomparator.dto.ProductSubstituteDTO;
import org.market.pricecomparator.exceptions.ResourceNotFound;
import org.market.pricecomparator.model.entity.PriceEntry;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.market.pricecomparator.model.enums.MeasurementUnit;
import org.market.pricecomparator.repository.PriceEntryRepository;
import org.market.pricecomparator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceEntryRepository priceEntryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, PriceEntryRepository priceEntryRepository) {
        this.productRepository = productRepository;
        this.priceEntryRepository = priceEntryRepository;
    }

    private static class NormalizedValue {
        Double quantity;
        String baseUnit;

        NormalizedValue(Double quantity, String baseUnit) {
            this.quantity = quantity;
            this.baseUnit = baseUnit;
        }
    }

    public Map<String, List<ProductSubstituteDTO>> getRecommendations(Long productId){

        Optional<Product> targetProduct = productRepository.findById(productId);

        if(targetProduct.isEmpty()) {
            throw new ResourceNotFound("Product not found");
        }

        String productName = targetProduct.get().getName();
        String brand = targetProduct.get().getBrand();

        Map<String, List<ProductSubstituteDTO>> result = new HashMap<>();

        List<Product> productVariations = productRepository.findByNameAndOptionalBrand(productName, brand);

        if(productVariations.isEmpty()) {
            productVariations = productRepository.findByNameAndOptionalBrand(productName, null);
        }

        for(Product productVariation : productVariations) {
            List<PriceEntry> latestPriceEntriesForEachStore = priceEntryRepository.findLatestPriceEntryInEachStoreByProductId(productVariation.getId());

            NormalizedValue normalizedValue = normalize(productVariation.getPackageQuantity(), productVariation.getUnit());

            if(normalizedValue.quantity <= 0 || "UNKNOWN".equals(normalizedValue.baseUnit)) {
                continue;
            }

            for(PriceEntry priceEntry : latestPriceEntriesForEachStore) {
                Store store = priceEntry.getStore();

                BigDecimal valuePerUnit = priceEntry.getPrice().divide(BigDecimal.valueOf(normalizedValue.quantity), 2, RoundingMode.HALF_UP);

                ProductSubstituteDTO product = new ProductSubstituteDTO(
                        productVariation.getName(),
                        productVariation.getBrand(),
                        valuePerUnit,
                        priceEntry.getPrice(),
                        normalizedValue.quantity,
                        productVariation.getPackageQuantity(),
                        normalizedValue.baseUnit,
                        productVariation.getUnit()
                );

                result.computeIfAbsent(store.getName(), k -> new ArrayList<>()).add(product);
            }

        }
        return result;
    }


    private NormalizedValue normalize(Double productQuantity, MeasurementUnit productUnit) {
        if(productQuantity == null || productUnit == null) {
            return new NormalizedValue(0D, "UNKNOWN");
        }

        switch (productUnit) {
            case G :
                return new NormalizedValue(productQuantity / 1000.0, "KILOGRAM");
            case KG:
                return new NormalizedValue(productQuantity, "KILOGRAM");
            case L:
                return new NormalizedValue(productQuantity, "LITER");
            case ML:
                return new NormalizedValue(productQuantity / 1000.0,"LITER");
            case PIECE:
                return new NormalizedValue(productQuantity, "PIECE");
            default:
                return new NormalizedValue(productQuantity, productUnit.name());
        }
    }
}


