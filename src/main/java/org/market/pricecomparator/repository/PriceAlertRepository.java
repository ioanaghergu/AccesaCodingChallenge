package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.PriceAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> {
    List<PriceAlert> findByUserIdAndIsActiveTrue(Long userId);
    Optional<PriceAlert> findByUserIdAndProductIdAndIsActiveTrue(Long userId, Long productId);
    List<PriceAlert> findAllByIsActiveTrue();

}
