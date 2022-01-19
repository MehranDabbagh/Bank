package Repositories;

import MyConnection.PostgresConnection;

import java.sql.*;

public class BankRepositories {
    Connection connection=PostgresConnection.getInstance().getConnection();
    public BankRepositories() throws ClassNotFoundException, SQLException {
        String sql="create table if not exists bank(branchName varchar(50) UNIQUE)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void create(String branchName){
        String sqlTest="select * from bank where branchName=? ";
        String sql="insert into bank (branchName) values (?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
            preparedStatement.setString(1,branchName);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("there is a branch with this name!");
            }else {
        preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,branchName);
            preparedStatement.execute();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
