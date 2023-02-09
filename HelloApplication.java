package com.example.eccom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailID;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailID="";
        connection= new DatabaseConnection();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("header.fxml"));
        root=new Group();
        Header header= new Header();

        ProductPage productPage=new ProductPage();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        root.getChildren().addAll(header.root,productPane);
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Eccomerce");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("Connection is closed Successfull");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}