package org.market.pricecomparator.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    @NotNull
    private Store store;

    @Column(nullable = false)
    @NotNull(message = "Discount percentage required")
    @DecimalMin(value = "0.1", message = "Discount percentage must be a positive number")
    @DecimalMax(value = "100.0", message = "Discount percentage can't exceed 100%")
    private BigDecimal percentage;

    @Column(nullable = false)
    @NotNull(message = "Start date of discount required")
    @FutureOrPresent(message = "Start date of discount can't be in the past")
    private LocalDate startDate;

    @Column(nullable = false)
    @NotNull(message = "End date of discount required")
    @Future(message = "End date of discount must be in the future")
    private LocalDate endDate;

    private LocalDate entryDate;
}
