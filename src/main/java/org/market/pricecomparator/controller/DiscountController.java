package org.market.pricecomparator.controller;

import org.market.pricecomparator.dto.DiscountDTO;
import org.market.pricecomparator.dto.StoreDTO;
import org.market.pricecomparator.model.entity.Store;
import org.market.pricecomparator.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/bestDiscounts")
    public ResponseEntity<List<StoreDTO>> getBestDiscounts() {
        List<StoreDTO> bestDiscountsPerStore = discountService.getBestDiscountsPerStore();
        return ResponseEntity.ok(bestDiscountsPerStore);
    }
}
