package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class Gui {
    //Переменные для объекта событий
    private int orderID;
    OrderRegistered OR;
    OrderCancel OC;
    OrderInWork OIW;
    OrderReady OReady;
    OrderDone OD;
    OrderEvent currentEvent;

    public Gui(){
        int c = 0;
        //Переменные для счетчика инкрементации id заказа и id клиента
        try(FileReader reader = new FileReader("order.txt"))
        {
            while((c=reader.read())!=-1){
                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        orderID = c;
        System.out.println(orderID);


        JFrame a = new JFrame("Система заказов в кофейне");

        JButton applyOrder = new JButton("Принять заказ");
        applyOrder.setBounds(10,50,200,20);

        JButton cancelOrder = new JButton("Отменить заказ");
        cancelOrder.setBounds(10,100,200,20);

        JButton takeOrderInWork = new JButton("Взять заказ в работу");
        takeOrderInWork.setBounds(10,150,200,20);

        JButton orderReady = new JButton("Заказ приготовлен");
        orderReady.setBounds(10,200,200,20);

        JButton giveOrder = new JButton("Выдать заказ");
        giveOrder.setBounds(10,250,200,20);

        JButton displayInfo = new JButton("Отобразить инфо-ию по заказу");
        displayInfo.setBounds(10,350,200,20);

        JLabel info = new JLabel("");
        info.setBounds(10,400,400,20);

        cancelOrder.setEnabled(false);
        takeOrderInWork.setEnabled(false);
        orderReady.setEnabled(false);
        giveOrder.setEnabled(false);
        displayInfo.setEnabled(false);

        //Команда принять заказ
        applyOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OR = new OrderRegistered(orderID, 15, 20, 35, 40, 13);
                OR.writeInBase();
                currentEvent = OR;

                cancelOrder.setEnabled(true);
                takeOrderInWork.setEnabled(true);
                orderReady.setEnabled(true);
                giveOrder.setEnabled(true);
                displayInfo.setEnabled(true);
                applyOrder.setEnabled(false);
            }
        });

        //Команда отменить заказ
        cancelOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(OR.isSet){
                    OC = new OrderCancel(orderID, 20, "Передумал");
                    OC.writeInBase();
                    currentEvent = OC;
                }
            }
        });


        //Команда взять заказ в работу
        takeOrderInWork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(OR.isSet){
                    OIW = new OrderInWork(orderID, 20);
                    OIW.writeInBase();
                    currentEvent = OIW;
                }
            }
        });

        //Команда готовности заказа
        orderReady.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(OR.isSet){
                    OReady = new OrderReady(orderID, 20);
                    OReady.writeInBase();
                    currentEvent = OReady;
                }
            }
        });


        //Команда выдача заказа
        giveOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(OR.isSet){
                    OD = new OrderDone(orderID, 20);
                    OD.writeInBase();
                    currentEvent = OD;


                    cancelOrder.setEnabled(false);
                    takeOrderInWork.setEnabled(false);
                    orderReady.setEnabled(false);
                    giveOrder.setEnabled(false);
                }
            }
        });


        //Получение инфо-ии о заказе, через объект Order, где реализован
        //интерфейс OrderService
        displayInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                String message = order.publishEvent(currentEvent);
                info.setText(message);
            }
        });



        //Добавление элементов на frame (каркас)
        a.add(applyOrder);
        a.add(cancelOrder);
        a.add(takeOrderInWork);
        a.add(orderReady);
        a.add(giveOrder);
        a.add(displayInfo);
        a.add(info);

        a.setSize(500,500);
        a.setLayout(null);
        a.setVisible(true);








    }
}
