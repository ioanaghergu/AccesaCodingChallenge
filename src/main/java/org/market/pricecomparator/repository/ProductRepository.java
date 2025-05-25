package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.enums.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndBrandAndPackageQuantityAndUnit(String name, String brand, Double packageQuantity, MeasurementUnit unit);

    @Query("SELECT p FROM Product p JOIN FETCH p.discounts d WHERE d.percentage = :discount AND d.store.id = :storeId")
    List<Product> findByStoreAndDiscount(Long storeId, BigDecimal discount);

    @Query("SELECT p FROM Product p WHERE p.name = :name AND (p.brand = :brand OR :brand IS NULL )")
    List<Product> findByNameAndOptionalBrand(String name, String brand);

}
