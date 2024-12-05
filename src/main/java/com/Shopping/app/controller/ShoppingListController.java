package com.Shopping.app.controller;

import com.Shopping.app.entity.ShoppingList;
import com.Shopping.app.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-lists")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping("/{id}")
    public ShoppingList getShoppingListById(@PathVariable Long id) {
        return shoppingListService.getShoppingListById(id);
    }

    @PostMapping
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.createShoppingList(shoppingList);
    }
}
