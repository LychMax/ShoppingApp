package com.Shopping.app.service;

import com.Shopping.app.entity.ShoppingList;
import com.Shopping.app.repository.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList getShoppingListById(Long id) {
        return shoppingListRepository.findById(id).orElse(null);
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }
}
