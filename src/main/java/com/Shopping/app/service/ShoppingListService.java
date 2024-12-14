package com.Shopping.app.service;

import com.Shopping.app.dto.AddItemToShoppingListDto;
import com.Shopping.app.dto.ShoppingListDto;

import java.util.List;

public interface ShoppingListService {
    List<ShoppingListDto> getAllShoppingLists();

    ShoppingListDto getShoppingListById(Long id);

    void createShoppingList(ShoppingListDto shoppingListDto);

    void addItemToShoppingList(AddItemToShoppingListDto dto);

    void updateItemQuantity(AddItemToShoppingListDto dto);

    void removeItemFromShoppingList(AddItemToShoppingListDto dto);

    void removeItemList(Long id);
}
