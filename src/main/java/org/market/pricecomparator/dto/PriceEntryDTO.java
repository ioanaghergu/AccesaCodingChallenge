package org.market.pricecomparator.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntryDTO {

    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long storeId;

    @NotNull(message = "Price required")
    @DecimalMin(value = "0.01", message = "Price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "Entry date required")
    private LocalDate entryDate;
}
