package org.market.pricecomparator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private Long id;

    @NotNull
    private Long userId;
    private List<ShoppingListDTO> shoppingLists;
}
