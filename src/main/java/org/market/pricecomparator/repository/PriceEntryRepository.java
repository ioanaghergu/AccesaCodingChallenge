package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.PriceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceEntryRepository extends JpaRepository<PriceEntry, Long> {

}
