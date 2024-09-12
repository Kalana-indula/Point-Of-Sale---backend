package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.PosOrder;
import com.example.demo.entity.Stock;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PosOrderRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosOrderServiceImpl implements PosOrderService{

    @Autowired
    private PosOrderRepository posOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public PosOrder createOrder(OrderDto orderDto) {

        List<Long> itemIdList=orderDto.getItems();

        List<Item> orderItems=new ArrayList<>();

        Double totalPrice=0.0;

        for(Long itemId:itemIdList){
            Item orderItem=itemRepository.findById(itemId).orElse(null);

            if(orderItem!=null){
                orderItems.add(orderItem);

                totalPrice=totalPrice+orderItem.getPrice();
                //Updating stock
                setItemStockStates(orderItem);
            }
        }

        if(!orderItems.isEmpty()){
            PosOrder newOrder=new PosOrder();

            Double tax=totalPrice*(3.0/100.0);
            totalPrice=totalPrice+tax;

            newOrder.setOrderTime(LocalDateTime.now());
            newOrder.setTotal(totalPrice);
            newOrder.setTax(tax);
            newOrder.setItems(orderItems);

            return posOrderRepository.save(newOrder);
        }else{
            return null;
        }

    }

    @Override
    public List<PosOrder> getAllOrders() {

        List<PosOrder> orders=posOrderRepository.findAll();

        return orders;
    }

    @Override
    public String deleteOrder(Long id) {

        Boolean isExist=posOrderRepository.existsById(id);

        if(isExist){
            posOrderRepository.deleteById(id);
            return "Order data deleted successfully";
        }else{
            return "No order data found";
        }
    }

    @Override
    public PosOrder getOrderById(Long id) {

        PosOrder order=posOrderRepository.findById(id).orElse(null);

        if(order!=null){
            return order;
        }else{
            return null;
        }
    }

    @Override
    public List<PosOrder> getLastOrders() {

        List<PosOrder> orders=posOrderRepository.findAll();

        //getting size of the list
        int totalOrders=orders.size();

        //Determine the starting index to get the last 10 ordres
        //If total orders are less than 10 , startIndex will be '0'
        int startIndex=Math.max(totalOrders-10,0);

        //Return a sublist of last 10 orders
        return orders.subList(startIndex,totalOrders);

    }

    @Override
    public int getOrdersOnCurrentDay(LocalDateTime startOfDay, LocalDateTime endOfDay) {

        List<PosOrder> currentDayOrders=posOrderRepository.findPosOrderByOrderDate(startOfDay,endOfDay);

        int numberOfOrders=currentDayOrders.size();

        return numberOfOrders;
    }

    @Override
    public double getAverageSalesOnCurrentDay(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        List<PosOrder> currentDayOrders=posOrderRepository.findPosOrderByOrderDate(startOfDay,endOfDay);

        double currentSalesValue=0.0;

        for(PosOrder order:currentDayOrders){
            currentSalesValue+=order.getTotal();
        }

        return currentSalesValue;
    }


    //Reduce order stock from current stock
    private void setItemStockStates(Item item){
        Stock itemStock=stockRepository.findStockByItem(item);

        Integer currentStock=itemStock.getStockQty();
        Integer newStockQty=currentStock-1;

        itemStock.setStockQty(newStockQty);

        stockRepository.save(itemStock);
    }

}
