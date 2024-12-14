package com.Shopping.app.repository;

import com.Shopping.app.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
    Optional<ShoppingListItem> findByShoppingListIdAndItemId(Long shoppingListId, Long itemId);
}
