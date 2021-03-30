package com.codecool.dungeoncrawl.saveLoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private final String db_url = "";
    private final String db_user_name = "";
    private final String db_password = "";

    private void connect(){
        try(Connection connection = DriverManager.getConnection(db_url, db_user_name, db_password)){
            if (connection != null) {
                System.out.println("Connection done!");
            }
            else {
                System.out.println("Connection error!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
