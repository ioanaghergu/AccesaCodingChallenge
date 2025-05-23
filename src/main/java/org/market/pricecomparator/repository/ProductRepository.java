package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void saveProductToDatabase(Product product);
}
