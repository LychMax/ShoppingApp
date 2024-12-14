package com.Shopping.app.dto;

import lombok.Data;

@Data
public class ShoppingListItemDto {
    private Long itemId;
    private String itemName;
    private Integer quantity;
}