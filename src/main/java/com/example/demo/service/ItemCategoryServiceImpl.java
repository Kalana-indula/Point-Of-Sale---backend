package com.example.demo.service;

import com.example.demo.entity.ItemCategory;
import com.example.demo.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    //Establishing the connectivity with data access layer
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> getAllItemCategories() {

        //getting all entities managed by ItemCategoryRepository by calling 'findAll()' method from JpaRepository
        List<ItemCategory> categories=itemCategoryRepository.findAll();

        if(!categories.isEmpty()){
            return categories;
        }else{
            return null;
        }

    }

    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory) {
        //saving passed object using save method in JpaRepository
        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {

        //find an ItemCategoryBy id using findById() method
        ItemCategory existingCategory=itemCategoryRepository.findById(id).orElse(null);

        if(existingCategory!=null){
            return existingCategory;
        }else{
            return null;
        }
    }

    @Override
    public ItemCategory updateItemCategory(Long id, ItemCategory itemCategory) {

        //find the existing category using findById method
        ItemCategory existingCategory=itemCategoryRepository.findById(id).orElse(null);

        //If there is an existing category for the corresponding id, then it is updated using new data provided by passed object
        if(existingCategory!=null){
           existingCategory.setName(itemCategory.getName());
           return itemCategoryRepository.save(existingCategory);
        }else{
            return null;
        }

    }

    @Override
    public String deleteItemCategory(Long id) {
        
        //Check if an ItemCategory is existing by given id
        Boolean isExist=itemCategoryRepository.existsById(id);

        //If a category is existing it is deleted
        if(isExist){
            itemCategoryRepository.deleteById(id);
            return "Category Successfully Deleted";
        }else{
            return "No Category Found For Entered Id";
        }

    }
}
