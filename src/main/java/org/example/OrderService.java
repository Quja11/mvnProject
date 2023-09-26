package org.example;

interface OrderService {

    String publishEvent(OrderEvent event);

    Order findOrder(int id);

}

