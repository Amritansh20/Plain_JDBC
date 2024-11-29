package com.example.plain_jdbc.Plain.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO{
    public void createUser(User user){
        String query = "INSERT INTO user(user_name, user_age) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            connection= DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUser_name());
            preparedStatement.setInt(2,user.getUser_age());
            preparedStatement.executeUpdate();
            System.out.println("User created successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public User getUser(int userId){
        String query = "SELECT * FROM user WHERE user_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection= DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_age(resultSet.getInt("user_age"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    public void closeStatement(PreparedStatement preparedStatement){
        if(preparedStatement !=null){
            try{
                preparedStatement.close();
                System.out.println("Statement closed successfully.");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void closeConnection(Connection connection){
        if(connection!=null){
            try{
                connection.close();
                System.out.println("ResultSet closed successfully.");
            }catch (SQLException e){
                System.err.println("Error closing ResultSet.");
                e.printStackTrace();
            }
        }
    }


    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                System.out.println("ResultSet closed successfully.");
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet.");
                e.printStackTrace();
            }
        }
    }

}