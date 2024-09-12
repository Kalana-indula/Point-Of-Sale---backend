package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.PosOrder;
import com.example.demo.service.PosOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PosOrderController {

    @Autowired
    private PosOrderService posOrderService;

    @PostMapping("/create/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto){

        try{
            PosOrder newOrder= posOrderService.createOrder(orderDto);

            if(newOrder!=null){
                return ResponseEntity.status(HttpStatus.OK).body(newOrder);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Entities Were Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        try{
            List<PosOrder> orders=posOrderService.getAllOrders();

            if(!orders.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(orders);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Orders Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        try{

            PosOrder order= posOrderService.getOrderById(id);

            if(order!=null){
                return ResponseEntity.status(HttpStatus.OK).body(order);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No corresponding order found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/latest/orders")
    public ResponseEntity<?> getLastOrders(){
        try{
            List<PosOrder> lastOrders=posOrderService.getLastOrders();

            if(!lastOrders.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(lastOrders);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/orders/today")
    public ResponseEntity<?> getCurrentDayOrders(LocalDateTime startOfDay,LocalDateTime endOfDay){

        LocalDateTime today=LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime nextDay=today.plusDays(1).minusSeconds(1);

        try{
            int currentDayOrdersCount=posOrderService.getOrdersOnCurrentDay(today,nextDay);

            return ResponseEntity.status(HttpStatus.OK).body(currentDayOrdersCount);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/sales/today")
    public ResponseEntity<?> getAverageSalesOnCurrentDay(){
        LocalDateTime today=LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime nexDay=today.plusDays(1).minusSeconds(1);

        try{
            double currentDaySales=posOrderService.getAverageSalesOnCurrentDay(today,nexDay);

            return ResponseEntity.status(HttpStatus.OK).body(currentDaySales);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
