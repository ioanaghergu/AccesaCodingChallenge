package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    void saveStoreToDatabase(Store store);
}
