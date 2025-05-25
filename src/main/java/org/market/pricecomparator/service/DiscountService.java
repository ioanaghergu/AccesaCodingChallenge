package org.market.pricecomparator.service;

import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.dto.ProductDTO;
import org.market.pricecomparator.exceptions.ResourceNotFound;
import org.market.pricecomparator.mapper.DiscountMapper;
import org.market.pricecomparator.mapper.ProductMapper;
import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.market.pricecomparator.repository.DiscountRepository;
import org.market.pricecomparator.repository.ProductRepository;
import org.market.pricecomparator.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final StoreRepository storeRepository;
    private final DiscountMapper discountMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, StoreRepository storeRepository,
                           DiscountMapper discountMapper, ProductRepository productRepository, ProductMapper productMapper) {
        this.discountRepository = discountRepository;
        this.storeRepository = storeRepository;
        this.discountMapper = discountMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Map<String, List<ProductDTO>> getBestDiscountsPerStore() {

        LocalDate today = LocalDate.now();

        List<Store> stores = storeRepository.findAll();

        if(stores.isEmpty()){
            throw new ResourceNotFound("No stores found");
        }

        Map<String, List<ProductDTO>> result = new HashMap<>();

        for (Store store : stores) {
            List<Discount> activeDiscounts = discountRepository.findActiveDiscountsByStore(store.getId(), today);

            if(activeDiscounts.isEmpty()){
                throw new RuntimeException("No active discounts found for store: " + store.getName());
            }



            BigDecimal maxDiscount = activeDiscounts.get(0).getPercentage();

            List<Product> products = productRepository.findByStoreAndDiscount(store.getId(), maxDiscount);


            if(products.isEmpty()){
                throw new RuntimeException("No products found for store: " + store.getName());
            }

            List<ProductDTO> resultProducts = products.stream()
                    .map(productMapper::toProductDTO)
                    .collect(Collectors.toList());

            if(!resultProducts.isEmpty()){
                result.put(store.getName(), resultProducts);
            }
        }

        return result;
    }

    public Map<String, List<DiscountDTO>> getNewDiscounts() {

        LocalDate today = LocalDate.now();

        List<Store> stores = storeRepository.findAll();

        if(stores.isEmpty()){
            throw new ResourceNotFound("No stores found");
        }

        Map<String, List<DiscountDTO>> result = new HashMap<>();

        for(Store store : stores){
            List<Discount> discounts = discountRepository.findNewlyAddedDiscountsByStore(store.getId(), today);

            if(discounts.isEmpty()){
                throw new ResourceNotFound("No active discounts found for store: " + store.getName());
            }

            List<DiscountDTO> discountDTOs = discounts.stream()
                            .map(discountMapper::toDiscountDTO)
                                    .collect(Collectors.toList());

            result.put(store.getName(), discountDTOs);
        }

        return result;

    }


}
