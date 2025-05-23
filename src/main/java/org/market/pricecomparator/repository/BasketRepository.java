package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

}
