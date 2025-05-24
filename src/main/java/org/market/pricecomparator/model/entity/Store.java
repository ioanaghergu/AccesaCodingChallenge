package org.market.pricecomparator.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
@ToString(exclude = {"priceEntries", "discounts"})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Store name required")
    private String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<PriceEntry> priceEntries = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Discount> discounts = new ArrayList<>();

    public void addPriceEntry(PriceEntry priceEntry) {
        priceEntries.add(priceEntry);
        priceEntry.setStore(this);
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
        discount.setStore(this);
    }
}
