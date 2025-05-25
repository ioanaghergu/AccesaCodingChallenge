package org.market.pricecomparator.controller;

import org.market.pricecomparator.dto.ProductSubstituteDTO;
import org.market.pricecomparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommendations")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Map<String, List<ProductSubstituteDTO>>> getRecommendations(@PathVariable Long productId) {
        Map<String, List<ProductSubstituteDTO>> recommendations = productService.getRecommendations(productId);
        return ResponseEntity.ok(recommendations);
    }
}
