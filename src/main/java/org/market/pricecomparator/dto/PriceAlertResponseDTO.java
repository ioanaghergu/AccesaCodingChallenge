package org.market.pricecomparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceAlertResponseDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private BigDecimal targetPrice;
    private Boolean isActive;
}
