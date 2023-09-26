package org.example;

public class Order implements OrderService{

    @Override
    public String publishEvent(OrderEvent event){
        return "Текущее событие: " + event.getClass();
    }

    @Override
    public Order findOrder(int id){
        Order order = new Order();
        return order;
    }





}
