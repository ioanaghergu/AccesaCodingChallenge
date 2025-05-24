package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.PriceEntry;

import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceEntryRepository extends JpaRepository<PriceEntry, Long> {
    Optional<PriceEntry> findPriceEntryByProductAndStoreAndEntryDate(Product product, Store store, LocalDate entryDate);

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.product p JOIN FETCH pe.store s WHERE p.id = :productId AND s.id = :storeId ORDER BY p.name ASC, pe.entryDate ASC")
    List<PriceEntry> findByProductAndStoreOrderByEntryDateAsc(Long productId, Long storeId);

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.product p JOIN FETCH pe.store s WHERE p.id = :productId AND p.brand = :brand  AND s.id = :storeId ORDER BY p.name ASC, pe.entryDate ASC")
    List<PriceEntry> findByProductAndBrandAndStoreIdOrderByProductNameAscEntryDateAsc(Long productId, String brand, Long storeId);

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.product p JOIN FETCH pe.store s WHERE p.id = :productId AND p.category = :category AND s.id = :storeId ORDER BY p.name ASC, pe.entryDate ASC")
    List<PriceEntry> findByProductAndCategoryAndStoreIdOrderByProductNameAscEntryDateAsc(Long productId, String category, Long storeId);

}
