package com.example.demo.service;

import com.example.demo.dto.StockUpdateDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Stock;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;

//    @Override
//    public Stock createStock(StockDto stockDto) {
//
//        Item item = itemRepository.findById(stockDto.getItemId()).orElse(null);
//
//        if(item!=null){
//
//            Stock stock=new Stock();
//            stock.setItem(item);
//            stock.setStockQty(stockDto.getStockQty());
//
//            return stockRepository.save(stock);
//        }else{
//            return null;
//        }
//    }

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockByItem(Long id) {

        Item item=itemRepository.findById(id).orElse(null);

        if(item!=null){

            return stockRepository.findStockByItem(item);
        }else{
            return null;
        }
    }

    @Override
    public Stock updateStock(Long id, StockUpdateDto stockUpdateDto) {
        //First Find the item corresponding to the id
        Item item=itemRepository.findById(id).orElse(null);

        //If item exists find it's stock by passing item as parameter
        if(item!=null){
            Stock existingStock=stockRepository.findStockByItem(item);

            existingStock.setStockQty(stockUpdateDto.getStockQty());
            return stockRepository.save(existingStock);
        }else{
            return null;
        }
    }

}
