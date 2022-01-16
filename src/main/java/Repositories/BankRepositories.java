package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public BankRepositories(){
        String sql="create table if not exists bank(branchName varchar(50) UNIQUE )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
