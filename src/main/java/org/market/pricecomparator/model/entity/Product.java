package org.market.pricecomparator.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.market.pricecomparator.model.enums.MeasurementUnit;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "brand", "package_quantity", "unit"})
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Product name required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Product category required")
    private String category;

    @Column(nullable = false)
    @NotBlank(message = "Product brand required")
    private String brand;

    @Column(nullable = false)
    @NotNull(message = "Package quantity required")
    @Positive(message = "Package quantity must be positive")
    private Double packageQuantity;

    @Column(nullable = false)
    @NotNull(message = "Package unit required")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    @Column(nullable = false)
    @NotBlank(message = "Product currency required")
    private String currency;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceEntry> priceEntries = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ShoppingListItem> shoppingListItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceAlert> priceAlerts = new ArrayList<>();
}
