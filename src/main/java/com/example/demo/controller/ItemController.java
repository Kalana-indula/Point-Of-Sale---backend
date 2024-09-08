package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create/items")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto){

        try{
            Item item= itemService.createItem(itemDto);

            if(item!=null){
                return ResponseEntity.status(HttpStatus.OK).body(item);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category Found For Entered Id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(){

        try{
            List<Item> items=itemService.getAllItems();
            return ResponseEntity.status(HttpStatus.OK).body(items);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id){

        try{
            Item item= itemService.getItemById(id);

            if(item!=null){
                return ResponseEntity.status(HttpStatus.OK).body(item);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found For Entered Id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/items/serial/{serialNumber}")
    public ResponseEntity<?> getItemBySerialNumber(@PathVariable String serialNumber){

        try{
            Item item= itemService.getItemBySerialNumber(serialNumber);

            if(item!=null){
                return ResponseEntity.status(HttpStatus.OK).body(item);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found For The Serial Number");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/update/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id,@RequestBody ItemDto itemDto){

        try{
            Item updatedItem= itemService.updateItem(id,itemDto);

            if(updatedItem!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Or Item Category Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){

        try{
            String deleteMessage=itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.OK).body(deleteMessage);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
