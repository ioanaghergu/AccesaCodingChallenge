package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByProductNameAndStoreNameAndStartDateAndEndDate(String productName, String storeName, LocalDate startDate, LocalDate endDate);
    List<Discount> findDiscountsByProductId(Long productId);
    List<Discount> findDiscountsByProductIdAndStoreId(Long productId, Long storeId);
}
