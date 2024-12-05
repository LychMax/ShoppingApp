package com.Shopping.app.controller;

import com.Shopping.app.entity.ShoppingListItem;
import com.Shopping.app.service.ShoppingListItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-list-items")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ShoppingListItemController {

    private final ShoppingListItemService shoppingListItemService;

    @PostMapping
    public ShoppingListItem addItemToShoppingList(@RequestBody ShoppingListItem shoppingListItem) {
        return shoppingListItemService.addItemToShoppingList(shoppingListItem);
    }
}
