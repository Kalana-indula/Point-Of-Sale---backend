package com.example.demo.controller;

import com.example.demo.dto.StockUpdateDto;
import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

//    @PostMapping("/create/stock")
//    public ResponseEntity<?> createStock(@RequestBody StockDto stockDto){
//
//        try{
//            Stock stock= stockService.createStock(stockDto);
//
//            if(stock!=null){
//                return ResponseEntity.status(HttpStatus.OK).body(stock);
//            }else{
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found For Corresponding Id");
//            }
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }

    @GetMapping("/stock")
    public ResponseEntity<?> getAllStocks(){
        try{
            List<Stock> allStocks=stockService.getAllStock();

            if(!allStocks.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(allStocks);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Stocks Available");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Stock is always referenced related to its item
    @GetMapping("/items/{id}/stock")
    public ResponseEntity<?> getStockByItem(@PathVariable Long id){

        try{
            Stock existingStock=stockService.getStockByItem(id);

            if(existingStock!=null){
                return ResponseEntity.status(HttpStatus.OK).body(existingStock);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found ");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //'stockUpdateDto' is used so that only quantity of stock can be updated
    @PutMapping("/update/items/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable Long id,@RequestBody StockUpdateDto stockUpdateDto){

        try{
            Stock updatedStock=stockService.updateStock(id,stockUpdateDto);

            if(updatedStock!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedStock);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found For Corresponding Id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
