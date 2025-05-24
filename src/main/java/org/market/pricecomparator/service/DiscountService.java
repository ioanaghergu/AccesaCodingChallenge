package org.market.pricecomparator.service;

import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.dto.StoreDTO;
import org.market.pricecomparator.exceptions.ResourceNotFound;
import org.market.pricecomparator.mapper.DiscountMapper;
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

    @Autowired
    public DiscountService(DiscountRepository discountRepository, StoreRepository storeRepository,
                           DiscountMapper discountMapper, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.storeRepository = storeRepository;
        this.discountMapper = discountMapper;
        this.productRepository = productRepository;
    }

    public List<StoreDTO> getBestDiscountsPerStore() {

        //should be LocalDate.now(), but there are no results in the database for current day
        LocalDate startDate = LocalDate.of(2025, 5, 10);

        List<Discount> activeDiscounts = discountRepository.findByStartDateBeforeAndEndDateAfter(startDate, startDate);

        if(activeDiscounts.isEmpty()) {
            throw new ResourceNotFound("There are no active discounts");
        }

        Map<Store, List<Discount>> activeDiscountsByStore = activeDiscounts.stream()
                .filter(discount -> discount.getStore() != null && discount.getPercentage() != null)
                .collect(Collectors.groupingBy(Discount::getStore));

        List<StoreDTO> result = new ArrayList<>();

        for (Map.Entry<Store, List<Discount>> entry : activeDiscountsByStore.entrySet()) {

            Store store = entry.getKey();
            List<Discount> storeDiscounts = entry.getValue();

            if (storeDiscounts.isEmpty()) {
                throw new RuntimeException("No active discounts for " + store.getName() + " store" );
            }

            Optional<BigDecimal> maxPercentageOpt = storeDiscounts.stream()
                    .map(Discount::getPercentage)
                    .max(Comparator.naturalOrder());

            if (maxPercentageOpt.isPresent()) {
                BigDecimal maxPercentage = maxPercentageOpt.get();

                List<Discount> topPercentageDiscountsForStore = storeDiscounts.stream()
                        .filter(d -> d.getPercentage().compareTo(maxPercentage) == 0)
                        .collect(Collectors.toList());

                for (Discount discount : topPercentageDiscountsForStore) {
                    Product product = productRepository.findById(discount.getProduct().getId()).orElse(null);
                    discount.setProduct(product);

                    Store store1 = storeRepository.findById(discount.getStore().getId()).orElse(null);
                    discount.setStore(store1);
                }

                store.setDiscounts(topPercentageDiscountsForStore);


                List<DiscountDTO> discountDTOs = topPercentageDiscountsForStore.stream()
                        .map(discountMapper::toDiscountDTO)
                        .collect(Collectors.toList());

                if (!discountDTOs.isEmpty()) {
                    result.add(new StoreDTO(
                            store.getId(),
                            store.getName(),
                            discountDTOs
                    ));
                }
            }
        }

        result.sort(Comparator.comparing(StoreDTO::getId));

        return result;
    }


}
