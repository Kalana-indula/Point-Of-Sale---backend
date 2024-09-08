package com.example.demo.service;

import com.example.demo.entity.ItemCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemCategoryService {

    //To get a list of ItemCategories
    List<ItemCategory> getAllItemCategories();

    //To create a new ItemCategory
    ItemCategory createItemCategory(ItemCategory itemCategory);

    //To get an ItemCategoryById
    ItemCategory getItemCategoryById(Long id);

    //To update an existing ItemCategory
    ItemCategory updateItemCategory(Long id, ItemCategory itemCategory);

    //To delete an existing ItemCategory
    String deleteItemCategory(Long id);
}
