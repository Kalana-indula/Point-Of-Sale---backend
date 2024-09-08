package com.example.demo.service;

import com.example.demo.dto.StockUpdateDto;
import com.example.demo.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {

    //Create a new stock
//    Stock createStock(StockDto stockDto);

    //Get all stock
    List<Stock> getAllStock();

    //Get stock for item
    Stock getStockByItem(Long id);

    //Update stock
    Stock updateStock(Long id, StockUpdateDto stockUpdateDto);

}
