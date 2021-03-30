package com.codecool.dungeoncrawl.saveLoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private void connect(){
        String db_url = "jdbc:postgresql://localhost:5432/postgres";
        String db_user_name = "postgres";
        String db_password = "Sx147DEs'";

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
