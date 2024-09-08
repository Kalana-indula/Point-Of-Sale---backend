package com.example.demo.repository;

import com.example.demo.entity.Item;
import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    //Finding stock corresponding to an item
    @Query("SELECT s FROM Stock s WHERE s.item= :item")
    Stock findStockByItem(@Param("item")Item item);
}
