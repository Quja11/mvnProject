package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class OrderReady extends OrderEvent{
    public String textSign = "Заказ готов к выдаче: ";
    public OrderReady(int orderID, int emplID){
        this.orderID = orderID;
        this.emplID = emplID;
    }

    public void writeInBase(){
        try(FileWriter writer = new FileWriter("events.txt", true))
        {
            String info = String.format("ID заказа: %s, ID сотрудника: %s,", orderID, emplID);
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
