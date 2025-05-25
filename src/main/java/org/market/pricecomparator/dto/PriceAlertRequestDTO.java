package org.market.pricecomparator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceAlertRequestDTO {

    @NotNull(message = "An alert should be set by a user")
    private Long userId;

    @NotNull(message = "An alert should be set on a product")
    private Long productId;

    @NotNull(message = "Target price required")
    @Positive(message = "Target price must be positive")
    private BigDecimal targetPrice;
}
