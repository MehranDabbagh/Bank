package Repositories;

import Entities.User;
import MyConnection.PostgresConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public UserRepositories(){
        String sql="create table if not exists users(national_code varchar(50) unique not null ,password  varchar(50) not null  )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createUser(String nationalCode,String password){
         String sql="insert into users(national_code,password ) values (?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,nationalCode);
            preparedStatement.setString(2,password);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User read(String nationalCode, String password){

        String sql="select * from  users where national_code=? and  password=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);;
            preparedStatement.setString(1, nationalCode);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                User user=new User(resultSet.getString("national_code"),resultSet.getString("password"));
               return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
