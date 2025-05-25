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

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.product p JOIN FETCH pe.store s WHERE p.id = :productId AND LOWER(p.brand) = LOWER(:brand) AND s.id = :storeId ORDER BY p.name ASC, pe.entryDate ASC")
    List<PriceEntry> findByProductAndBrandAndStoreIdOrderByProductNameAscEntryDateAsc(Long productId, String brand, Long storeId);

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.product p JOIN FETCH pe.store s WHERE p.id = :productId AND LOWER(p.category) = LOWER(:category) AND s.id = :storeId ORDER BY p.name ASC, pe.entryDate ASC")
    List<PriceEntry> findByProductAndCategoryAndStoreIdOrderByProductNameAscEntryDateAsc(Long productId, String category, Long storeId);

    @Query("SELECT pe FROM PriceEntry pe JOIN FETCH pe.store s JOIN FETCH pe.product p WHERE p.id = :productId AND pe.entryDate = " +
            "(SELECT MAX (pe_inner.entryDate) FROM PriceEntry pe_inner JOIN pe_inner.product p_inner JOIN pe_inner.store s_inner WHERE p_inner.id = p.id AND s_inner.id = s.id) ORDER BY s.name ASC ")
    List<PriceEntry> findLatestPriceEntryInEachStoreByProductId(Long productId);

}
