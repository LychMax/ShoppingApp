package com.Shopping.app.service;

import com.Shopping.app.dto.ItemDto;
import com.Shopping.app.entity.Item;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItems();

    ItemDto getItemById(Long id);

    void createItem(Item itemDto);

    void deleteItem(Long id);
}
