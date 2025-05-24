package org.market.pricecomparator.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_list_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"shopping_list_id", "product_id"})
})
@ToString(exclude = {"shoppingList", "product"})
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id", nullable = false)
    @NotNull
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    @Column(nullable = false)
    @Min(value = 1, message = "Quantity of a item in the shopping list must be at least 1")
    private Integer quantity;
}
