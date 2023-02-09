package com.example.eccom;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name,price,sellerid;
    @FXML
    public void logout(MouseEvent e) throws IOException, SQLException {
        HelloApplication.emailID="";

        ProductPage productPage=new ProductPage();
        Header header=new Header();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {
        int productID=1;
        ResultSet response2= HelloApplication.connection.executeQuery("select max(productID) as productID from product");
        if(response2.next()){
            productID=response2.getInt("productID")+1;
        }
        String query=String.format("Insert into product values(%s,'%s','%s','%s')",productID,name.getText(),price.getText(),sellerid.getText());
        int response= HelloApplication.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New product is Added");
        }
    }
    @FXML
    TextField productid;
    @FXML
    public void Delist(MouseEvent e) throws SQLException {
        String query= String.format("(delete from products where productID='%s')",productid.getText());
        HelloApplication.connection.delistQuery(query);
        System.out.println("Product de-listed successfully"+productid.getText());
    }
}
