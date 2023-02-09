package com.example.eccom;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("select max(OrderID) as OrderID from orders");
        int orderID =1;
        if(res.next()){
            orderID= res.getInt("orderID")+1;
        }
        //Date date=new Date(System.currentTimeMillis());
        Timestamp date=new Timestamp(Calendar.getInstance().getTime().getTime());
        String query= String.format("insert into orders values(%s,%s,'%s','%s')",
                orderID,productID,HelloApplication.emailID,date);
        int response = HelloApplication.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Order Status");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your Order is placed");
            dialog.showAndWait();
            //System.out.println("The order is placed "+date);
        }
        else{
            System.out.println("The order is not placed");
        }
    }
}
