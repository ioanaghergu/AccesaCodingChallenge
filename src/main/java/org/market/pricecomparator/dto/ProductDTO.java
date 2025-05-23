package org.market.pricecomparator.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.market.pricecomparator.model.enums.MeasurementUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Product name required")
    private String name;

    @NotBlank(message = "Product category required")
    private String category;

    @NotBlank(message = "Product brand required")
    private String brand;

    @NotNull(message = "Product package quantity required")
    @Positive(message = "Package quantity must be a positive number")
    private Double packageQuantity;

    @NotNull(message = "Package unit required")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    @NotNull(message = "Product currency required")
    private String currency;

}
