package com.Shopping.app.controller;

import com.Shopping.app.dto.AddItemToShoppingListDto;
import com.Shopping.app.dto.ShoppingListDto;
import com.Shopping.app.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    public List<ShoppingListDto> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping("/{id}")
    public ShoppingListDto getShoppingListById(@PathVariable Long id) {
        return shoppingListService.getShoppingListById(id);
    }

    @PostMapping
    public void createShoppingList(@RequestBody ShoppingListDto shoppingListDto) {
        shoppingListService.createShoppingList(shoppingListDto);
    }

    @PostMapping("/add-item")
    public void addItemToShoppingList(@RequestBody AddItemToShoppingListDto dto) {
        shoppingListService.addItemToShoppingList(dto);
    }

    @PutMapping("/update-item-quantity")
    public void updateItemQuantity(@RequestBody AddItemToShoppingListDto dto) {
        shoppingListService.updateItemQuantity(dto);
    }

    @DeleteMapping("/remove-item")
    public void removeItemFromShoppingList(@RequestBody AddItemToShoppingListDto dto) {
        shoppingListService.removeItemFromShoppingList(dto);
    }
}
