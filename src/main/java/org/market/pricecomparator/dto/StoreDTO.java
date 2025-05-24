package org.market.pricecomparator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private Long id;

    @NotNull(message = "Store name required")
    private String name;

    private List<DiscountDTO> discounts;
}
