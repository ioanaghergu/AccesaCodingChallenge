package org.market.pricecomparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.market.pricecomparator.model.enums.MeasurementUnit;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubstituteDTO {
    private String name;
    private String brand;
    private BigDecimal valuePerUnit;
    private BigDecimal price;
    private Double normalizedQuantity;
    private Double packageQuantity;
    private String baseUnit;
    private MeasurementUnit unit;

}
