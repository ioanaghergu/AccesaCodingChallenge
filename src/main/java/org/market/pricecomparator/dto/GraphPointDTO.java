package org.market.pricecomparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphPointDTO {
    private String productName;
    private BigDecimal price;
    private LocalDate entryDate;
}
