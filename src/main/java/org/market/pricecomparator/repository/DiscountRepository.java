package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByProductAndStoreAndStartDateAndEndDate(Product product, Store store, LocalDate startDate, LocalDate endDate);
    List<Discount> findByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);
    Optional<Discount> findTopByStoreOrderByPercentageDesc(Store store);
    List<Discount> findAllProductIdByPercentage(BigDecimal percentage);
}
