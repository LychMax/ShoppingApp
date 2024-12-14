package com.Shopping.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingListDto {
    private Long id;
    private String name;
    private List<ShoppingListItemDto> items;
}