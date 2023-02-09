package com.example.eccom;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage  {
    ListView<HBox> products;
    ListView<HBox> productsbySearch(String search) throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("select * from product");
        while(res.next()) {
            if (res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productID = new Label();
                Label price = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();

                name.setText(res.getString("productName"));
                productID.setText(res.getString("productID"));
                price.setText(res.getString("price"));
                buy.setText("Buy");
                name.setMinWidth(50);
                price.setMinWidth(50);
                productID.setMinWidth(50);

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailID.equals("")) {
                            System.out.println("Please Login first");
                        } else {
                            System.out.println("You are logged in with " + HelloApplication.emailID);
                            Order order = new Order();
                            try {
                                order.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        //System.out.println("buy button is clicked");
                    }
                });

                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }
        products.setItems(productList);
        return products;
    }
    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("select * from product");
        while(res.next()){
            Label name = new Label();
            Label productID =new Label();
            Label price=new Label();
            Button buy=new Button();
            HBox productDetails= new HBox();

            name.setText(res.getString("productName"));
            productID.setText(res.getString("productID"));
            price.setText(res.getString("price"));
            buy.setText("Buy");
            name.setMinWidth(50);
            price.setMinWidth(50);
            productID.setMinWidth(50);

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailID.equals("")){

                        Dialog<String> dialog= new Dialog<>();
                        dialog.setTitle("Alert");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("Please Login First");
                        dialog.showAndWait();
                        //System.out.println("Please Login first");
                    }
                    else{
                        System.out.println("You are logged in with "+HelloApplication.emailID);
                        Order order=new Order();
                        try {
                            order.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    //System.out.println("buy button is clicked");
                }
            });

            productDetails.getChildren().addAll(productID, name, price, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
