package com.Shopping.app.service.impl;

import com.Shopping.app.dto.ItemDto;
import com.Shopping.app.entity.Item;
import com.Shopping.app.repository.ItemRepository;
import com.Shopping.app.service.ItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No item found with ID: " + id));
        return modelMapper.map(item, ItemDto.class);
    }

    @Override
    public void createItem(Item itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
