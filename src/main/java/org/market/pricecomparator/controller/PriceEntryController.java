package org.market.pricecomparator.controller;

import org.market.pricecomparator.dto.GraphPointDTO;
import org.market.pricecomparator.service.PriceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filterPriceEntries")
public class PriceEntryController {

    private final PriceEntryService priceEntryService;

    @Autowired
    public PriceEntryController(PriceEntryService priceEntryService) {
        this.priceEntryService = priceEntryService;
    }

    @GetMapping("/by-store")
    public ResponseEntity<Map<String, List<GraphPointDTO>>>getByProductAndStore(@RequestParam("productId") Long productId, @RequestParam("storeId") Long storeId) {
        Map<String, List<GraphPointDTO>> points = priceEntryService.getPointsByProductAndStore(productId, storeId);
        return ResponseEntity.ok(points);
    }

    @GetMapping("by-brand")
    public ResponseEntity<Map<String, List<GraphPointDTO>>> getByStoreAndBrand(@RequestParam("productId") Long productId, @RequestParam("storeId") Long storeId, @RequestParam String brand) {
        Map<String, List<GraphPointDTO>> points = priceEntryService.getPointsByStoreAndBrand(productId, storeId, brand);
        return ResponseEntity.ok(points);
    }

    @GetMapping("by-category")
    public ResponseEntity<Map<String, List<GraphPointDTO>>> getByStoreAndCategory(@RequestParam("productId") Long productId, @RequestParam("storeId") Long storeId, @RequestParam String category) {
        Map<String, List<GraphPointDTO>> points = priceEntryService.getPointsByStoreAndCategory(productId, storeId, category);
        return ResponseEntity.ok(points);
    }


}
