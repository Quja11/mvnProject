package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class OrderRegistered extends OrderEvent{
    public String textSign = "Заказ зарегистрирован: ";
    private int clientID;
    private int timeOnOrder;
    private int productID;
    private int productPrice;
    public boolean isSet = false;

    public OrderRegistered(int orderID, int clientID, int emplID, int timeOnOrder, int productID, int productPrice){
        this.orderID = orderID;
        this.clientID = clientID;
        this.emplID = emplID;
        this.timeOnOrder = timeOnOrder;
        this.productID = productID;
        this.productPrice = productPrice;
        this.isSet = true;
    }

    public void writeInBase(){
        try(FileWriter writer = new FileWriter("events.txt", true))
        {
            String info = String.format("ID заказа: %s, ID клиента: %s, ID сотрудника: %s, Ожид.время выдачи: %s, ID товара: %s, Стоимость товара: %s", orderID, clientID,
                    emplID, timeOnOrder, productID, productPrice);
            writer.write(textSign);
            writer.write(info);

            writer.append('\n');

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }






}
