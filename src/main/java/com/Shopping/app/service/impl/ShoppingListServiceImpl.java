package com.Shopping.app.service.impl;

import com.Shopping.app.dto.AddItemToShoppingListDto;
import com.Shopping.app.dto.ShoppingListDto;
import com.Shopping.app.entity.Item;
import com.Shopping.app.entity.ShoppingList;
import com.Shopping.app.entity.ShoppingListItem;
import com.Shopping.app.repository.ItemRepository;
import com.Shopping.app.repository.ShoppingListItemRepository;
import com.Shopping.app.repository.ShoppingListRepository;
import com.Shopping.app.service.ShoppingListService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ShoppingListDto> getAllShoppingLists() {
        return shoppingListRepository.findAll().stream()
                .map(shoppingList -> modelMapper.map(shoppingList, ShoppingListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingListDto getShoppingListById(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found with ID: " + id));

        return modelMapper.map(shoppingList, ShoppingListDto.class);
    }

    @Override
    public void createShoppingList(ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = modelMapper.map(shoppingListDto, ShoppingList.class);
        shoppingListRepository.save(shoppingList);
    }

    @Override
    public void addItemToShoppingList(AddItemToShoppingListDto dto) {
        ShoppingList shoppingList = shoppingListRepository.findById(dto.getShoppingListId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found with ID: " + dto.getShoppingListId()));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found with ID: " + dto.getItemId()));

        ShoppingListItem shoppingListItem = new ShoppingListItem();
        shoppingListItem.setShoppingList(shoppingList);
        shoppingListItem.setItem(item);
        shoppingListItem.setQuantity(dto.getQuantity());

        shoppingListItemRepository.save(shoppingListItem);
    }

    @Override
    public void updateItemQuantity(AddItemToShoppingListDto dto) {
        ShoppingListItem shoppingListItem = shoppingListItemRepository.findByShoppingListIdAndItemId(dto.getShoppingListId(), dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found in shopping list"));

        shoppingListItem.setQuantity(dto.getQuantity());
        shoppingListItemRepository.save(shoppingListItem);
    }

    @Override
    public void removeItemFromShoppingList(AddItemToShoppingListDto dto) {
        ShoppingListItem shoppingListItem = shoppingListItemRepository.findByShoppingListIdAndItemId(dto.getShoppingListId(), dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found in shopping list"));

        shoppingListItemRepository.delete(shoppingListItem);
    }
}
