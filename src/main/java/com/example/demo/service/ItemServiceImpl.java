package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.Stock;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(ItemDto itemDto) {

        //Checking if an item is existing for the corresponding category id
        ItemCategory itemCategory=itemCategoryRepository.findById(itemDto.getItemCategoryId()).orElse(null);

        //If exists , then create a new object of 'Item' type
        if(itemCategory!=null){
            Item item=new Item();

            //Set values to the new object using dto values
            item.setSerialNumber(itemDto.getSerialNumber().toUpperCase());
            item.setItemName(itemDto.getItemName());
            item.setPrice(itemDto.getPrice());
            item.setItemCategory(itemCategory);

            //Create new stock entity as item is created
            Stock stock=new Stock();
            stock.setItem(item);
            stock.setStockQty(0);
            item.setStock(stock);

            return itemRepository.save(item);
        }else{
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        //Getting all the items
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {

        Item item=itemRepository.findById(id).orElse(null);

        if(item!=null){
            return item;
        }else{
            return null;
        }
    }

    @Override
    public Item getItemBySerialNumber(String serialNumber) {

        Item item=itemRepository.findItemBySerialNumber(serialNumber);

        if(item!=null){
            return item;
        }else{
            return null;
        }

    }

    @Override
    public Item updateItem(Long id, ItemDto itemDto) {

        Item existingItem=itemRepository.findById(id).orElse(null);
        ItemCategory existingItemCategory=itemCategoryRepository.findById(itemDto.getItemCategoryId()).orElse(null);

        //Update the item only given item is existed or itemCategory is existed
        if((existingItem!=null)&&(existingItemCategory!=null)){

            existingItem.setSerialNumber(itemDto.getSerialNumber());
            existingItem.setItemName(itemDto.getItemName());
            existingItem.setPrice(itemDto.getPrice());
            existingItem.setItemCategory(existingItemCategory);

            return itemRepository.save(existingItem);
        }else{
            return null;
        }
    }

    @Override
    public String deleteItem(Long id) {

        Boolean isExists=itemRepository.existsById(id);

        if(isExists){
            itemRepository.deleteById(id);
            return "Item Deleted Successfully";
        }else{
            return "No Item Found For The Corresponding Id";
        }
    }
}
