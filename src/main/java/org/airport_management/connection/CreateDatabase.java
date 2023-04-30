package org.airport_management.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "Mariam2905";

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            String sql = "create database AirportManagementSystem";
            stmt.executeUpdate(sql);
            System.out.println("The database airport_management is created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
