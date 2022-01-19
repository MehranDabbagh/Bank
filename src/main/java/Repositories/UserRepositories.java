package Repositories;

import Entities.User;
import MyConnection.PostgresConnection;


import java.sql.*;

public class UserRepositories {
    Connection connection=PostgresConnection.getInstance().getConnection();
    public UserRepositories() throws ClassNotFoundException, SQLException {
        String sql="create table if not exists users(national_code varchar(50) unique not null ,password  varchar(50) not null ,branchName )";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createUser(String nationalCode,String password,String branchName){
        String sqlBranchTest="select from bank where branchName=?";
         String sql="insert into users(national_code,password,branchName ) values (?,?,?)";
         String sqlTest="select from users where national_code=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sqlTest);
            preparedStatement.setString(1,nationalCode);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("there is a user with this national code!");
            }else {
                preparedStatement=connection.prepareStatement(sqlBranchTest);
                preparedStatement.setString(1,branchName);
                ResultSet resultSet1=preparedStatement.executeQuery();
                if(resultSet1.next()){
           preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,nationalCode);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,branchName);
            preparedStatement.execute();}else System.out.println("there is no branch with this  name");}
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
