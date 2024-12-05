package com.Shopping.app.service;

import com.Shopping.app.entity.ShoppingListItem;
import com.Shopping.app.repository.ShoppingListItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ShoppingListItemService {

    private final ShoppingListItemRepository shoppingListItemRepository;

    public ShoppingListItem addItemToShoppingList(ShoppingListItem shoppingListItem) {
        return shoppingListItemRepository.save(shoppingListItem);
    }
}
