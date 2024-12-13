package com.Shopping.app.dto;

import lombok.Data;

@Data
public class AddItemToShoppingListDto {
    private Long shoppingListId;
    private Long itemId;
    private Integer quantity;
}
