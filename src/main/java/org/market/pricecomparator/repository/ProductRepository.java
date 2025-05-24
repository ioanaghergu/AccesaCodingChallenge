package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.enums.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndBrandAndPackageQuantityAndUnit(String name, String brand, Double packageQuantity, MeasurementUnit unit);

}
