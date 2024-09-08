package com.example.demo.repository;

import com.example.demo.entity.PosOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PosOrderRepository extends JpaRepository<PosOrder,Long> {

    @Query("SELECT o FROM PosOrder o WHERE o.orderTime BETWEEN :startOfDay AND :endOfDay")
    List<PosOrder> findPosOrderByOrderDate(
            @Param("startOfDay")LocalDateTime startOfDay,
            @Param("endOfDay")LocalDateTime endOfDay);

}
