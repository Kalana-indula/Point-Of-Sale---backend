package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    //create a new item
    Item createItem(ItemDto itemDto);

    //Getting all the items
    List<Item> getAllItems();

    //Get an item for a given id
    Item getItemById(Long id);

    //Get item by it's serial number
    Item getItemBySerialNumber(String serialNumber);

    //Update an available item
    Item updateItem(Long id,ItemDto itemDto);

    //Delete an existing item
    String deleteItem(Long id);

}
