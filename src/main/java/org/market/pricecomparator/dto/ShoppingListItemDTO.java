package org.market.pricecomparator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListItemDTO {
    private Long id;

    @NotNull
    private Long shoppingListId;

    @NotNull
    private Long productId;

    @Min(value = 1, message = "Product quantity must be at least 1")
    private Integer quantity;
}
