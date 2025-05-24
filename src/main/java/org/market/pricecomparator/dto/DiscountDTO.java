package org.market.pricecomparator.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long storeId;

    @NotNull(message = "Discount percentage required")
    @DecimalMin(value = "0.1", message = "Discount percentage must be a positive number")
    @DecimalMax(value = "100.0", message = "Discount percentage can't exceed 100%")
    private BigDecimal percentage;

    @NotNull(message = "Start date of discount required")
    @FutureOrPresent(message = "Start date of discount can't be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date of discount required")
    @Future(message = "End date of discount must be in the future")
    private LocalDate endDate;

    private LocalDate entryDate;
}
