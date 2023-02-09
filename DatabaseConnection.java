package com.example.eccom;
import java.sql.*;

public class DatabaseConnection {
    Connection con=null;
    String SQLURL ="jdbc:mysql://localhost:3306/eccom?useSSL=false";
    String username= "root";
    String password="qwertyuio0";
    DatabaseConnection() throws SQLException {
        con= DriverManager.getConnection(SQLURL,username,password);
        if(con!=null){
            System.out.println("Our connection is Established with the database");
        }
    }
    // select * from temporary
    public ResultSet executeQuery(String query){
        ResultSet result=null;
        try{
            Statement statement=con.createStatement();
            result =statement.executeQuery(query);
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public void delistQuery(String query) throws SQLException {
        Statement statement = con.createStatement();
        //statement.delistQuery(query);
        return;
    }
    // for updating
     public int executeUpdate(String query){
        int row=0;
        try{
            Statement statement=con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return row;
     }
}
