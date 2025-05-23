package org.market.pricecomparator.repository;

import org.market.pricecomparator.model.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
    List<ShoppingListItem> getAllShoppingListItemsByShoppingListId(Long shoppingListId);
}
