package com.example.eccom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query=String.format("Select * from user where emailID= '%s' AND pass='%s'",email.getText(),password.getText());
        // query= "select * from user where emailID='robin@gmail.com' and pass='1234'";
        ResultSet res=HelloApplication.connection.executeQuery(query);
        if(res.next()){
            HelloApplication.emailID=res.getString("emailID");
            String userType = res.getString("userType");
            if(userType.equals("Seller")){
                AnchorPane sellerPage= FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                HelloApplication.root.getChildren().add(sellerPage);
            }
            else{
                System.out.println("We are logged as Buyer");
                ProductPage productPage=new ProductPage();

                Header header=new Header();
                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(150);
                productPane.setLayoutY(100);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);
            }
            System.out.println("The user is present in the User Table");
        }
        else{
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Login Status");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed: Please check username/password");
            dialog.showAndWait();
            //System.out.println("the user is not present or password is wrong");
        }
    }


}
