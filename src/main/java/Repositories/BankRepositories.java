package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public BankRepositories(){
        String sql="create table if not exists bank(branchName varchar(50) UNIQUE)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void create(String branchName){
        String sql="insert into bank (branchName) values (?) where not exists(select * from bank where branchName=?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,branchName);
            preparedStatement.setString(2,branchName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
