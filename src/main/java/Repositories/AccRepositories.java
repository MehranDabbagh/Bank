package Repositories;



import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public AccRepositories(){
        String sql="create table if not exists accs(accId serial primary key,Status acc_status,password varchar(50),\n" +
                "    amount integer ,\n" +
                "    branchName varchar(50),\n" +
                "    userID VARCHAR (50) ,\n" +
                "    CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(userId) REFERENCES users(national_code) )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAcc(String username,String password,String branchName,String userId){

    }

}
