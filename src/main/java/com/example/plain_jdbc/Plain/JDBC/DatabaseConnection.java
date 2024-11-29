package com.example.plain_jdbc.Plain.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection{
    private  static  final String URL = "jdbc:mysql://localhost:3306/";
    private static  final String USER ="root";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DB_NAME = "plain_jdbc_demo";

    private static final String URL_WITH_DB = "jdbc:mysql://localhost:3306/plain_jdbc_demo";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL_WITH_DB, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public static void createDatabase(){
        String query = "CREATE DATABASE IF NOT EXISTS "  + DB_NAME;
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Database created or already exits");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void createUserTable(){
        String useDbQuery = "USE plain_jdbc_demo";

        String query = "CREATE TABLE IF NOT EXISTS user (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "user_name VARCHAR(50), " +
                "user_age INT)";

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            statement.executeUpdate(useDbQuery);
            statement.executeUpdate(query);
            System.out.println("Users table created or already exists.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  static  void closeConnection(Connection connection){
        if(connection!=null){

            try{
                connection.close();
                System.out.println("Connection closed successfully");
            }catch (SQLException e){
                System.err.println("Error closing connection");
                e.printStackTrace();
            }
        }
    }
}