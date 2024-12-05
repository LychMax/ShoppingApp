package com.Shopping.app.controller;

import com.Shopping.app.entity.Item;
import com.Shopping.app.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public void createItem(@RequestBody Item item) {
        try {
            itemService.createItem(item);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating item: " + e.getMessage());
        }
    }
}
