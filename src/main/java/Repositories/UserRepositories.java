package Repositories;

import Entities.User;
import MyConnection.PostgresConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public UserRepositories(){
        String sql="create table if not exists users(national_code varchar(50) primary key)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
