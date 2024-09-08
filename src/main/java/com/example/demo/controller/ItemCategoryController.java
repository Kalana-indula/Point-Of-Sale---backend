package com.example.demo.controller;

import com.example.demo.entity.ItemCategory;
import com.example.demo.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ItemCategoryController {
    //Establishing the connectivity with service layer
    @Autowired
    private ItemCategoryService itemCategoryService;

    //Creating an api for get all categories
    @GetMapping("/categories")
    public ResponseEntity<?> findAllCategories(){

        try{
            List<ItemCategory> categories=itemCategoryService.getAllItemCategories();

            if(categories!=null){
                return ResponseEntity.status(HttpStatus.OK).body(categories);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category Found In Records");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Create a new category
    @PostMapping("/create/categories")
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategory itemCategory){

        try{
            ItemCategory newCategory=itemCategoryService.createItemCategory(itemCategory);
            return ResponseEntity.status(HttpStatus.OK).body(newCategory);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Find item category for a given id
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getItemCategoryById(@PathVariable Long id){

        try{
            ItemCategory itemCategory= itemCategoryService.getItemCategoryById(id);

            if(itemCategory!=null){
                return ResponseEntity.status(HttpStatus.OK).body(itemCategory);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Category Found For Corresponding Id");
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Editing a details of an item category
    @PutMapping("/update/categories/{id}")
    public ResponseEntity<?> updateItemCategory(@PathVariable Long id,@RequestBody ItemCategory itemCategory){

        try{
            ItemCategory updatedItemCategory=itemCategoryService.updateItemCategory(id,itemCategory);

            if(updatedItemCategory!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedItemCategory);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Category Found For Corresponding Id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Deleting an existing item category
    @DeleteMapping("/delete/categories/{id}")
    public ResponseEntity<?> deleteItemCategory(@PathVariable Long id){

        try{
            String deleteMessage= itemCategoryService.deleteItemCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(deleteMessage);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
