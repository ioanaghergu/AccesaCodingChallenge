package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.enums.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndBrandAndPackageQuantityAndUnit(String name, String brand, Double packageQuantity, MeasurementUnit unit);

    @Query("SELECT p FROM Product p JOIN FETCH p.discounts d WHERE d.id = :discountId AND d.store.id = :storeId")
    List<Product> findByStoreAndDiscount(Long storeId, Long discountId);

}
