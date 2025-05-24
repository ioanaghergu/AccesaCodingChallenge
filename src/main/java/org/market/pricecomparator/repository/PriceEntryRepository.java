package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.PriceEntry;

import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PriceEntryRepository extends JpaRepository<PriceEntry, Long> {
    Optional<PriceEntry> findPriceEntryByProductAndStoreAndEntryDate(Product product, Store store, LocalDate entryDate);
    Optional<PriceEntry> findTopByProductAndStoreOrderByEntryDateDesc(Product product, Store store);
}
