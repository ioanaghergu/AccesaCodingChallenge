package org.market.pricecomparator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDTO {
    private Long id;

    @NotNull
    private Long basketId;

    @NotBlank(message = "Shopping list title required")
    private String title;

    private List<ShoppingListItemDTO> items;
}
