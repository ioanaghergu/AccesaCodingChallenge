package org.market.pricecomparator.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceAlertDTO {
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long userId;

    @NotNull(message = "Target price required")
    @DecimalMin(value = "0.01", message = "Target price must be a positive number")
    private BigDecimal targetPrice;

    private Boolean isActive;
}
