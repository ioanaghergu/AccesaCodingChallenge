package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByProductAndStoreAndStartDateAndEndDate(Product product, Store store, LocalDate startDate, LocalDate endDate);

    @Query("SELECT d FROM Discount d JOIN d.store s WHERE s.id = :storeId AND  d.startDate <= :date AND d.endDate >= :date ORDER BY d.percentage DESC")
    List<Discount> findActiveDiscountsByStore(Long storeId, LocalDate date);


    @Query("SELECT d FROM Discount d JOIN d.store s WHERE s.id = :storeId AND d.startDate = :date")
    List<Discount> findNewlyAddedDiscountsByStore(Long storeId, LocalDate date);
}
