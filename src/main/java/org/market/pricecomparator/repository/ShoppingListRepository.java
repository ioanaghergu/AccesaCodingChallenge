package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    List<ShoppingList> getShoppingListsByBasketId(Long basketId);
}
