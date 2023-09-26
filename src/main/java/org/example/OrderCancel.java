package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class OrderCancel extends OrderEvent{
    public String textSign = "Заказ отменен: ";
    private String causeCancel;

    public OrderCancel(int orderID, int emplID, String causeCancel){
        this.orderID = orderID;
        this.emplID = emplID;
        this.causeCancel = causeCancel;
    }

    public void writeInBase(){
        try(FileWriter writer = new FileWriter("events.txt", true))
        {
            String info = String.format("ID заказа: %s, ID сотрудника: %s," +
                            "Причина отмены: %s", orderID, emplID, causeCancel);
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
