package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.PosOrder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PosOrderService {

    //Create an order
    PosOrder createOrder(OrderDto orderDto);

    //Get all orders as a list
    List<PosOrder> getAllOrders();

    //Delete an order and get a string as output
    String deleteOrder(Long id);

    //Get order details by id
    PosOrder getOrderById(Long id);

    //get last 10 orders
    List<PosOrder> getLastOrders();

    //Get all the orders made on current day
    int getOrdersOnCurrentDay(LocalDateTime startOfDay,LocalDateTime endOfDay);
}
