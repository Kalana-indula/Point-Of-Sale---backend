package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.serialNumber = :serialNumber")
    Item findItemBySerialNumber(@Param("serialNumber")String serialNumber);
}
