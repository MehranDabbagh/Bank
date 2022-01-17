package Repositories;



import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public AccRepositories(){
        String sql="create table if not exists accs(accId varchar(50) primary key,Status acc_status not null,password varchar(50) not null,\n" +
                "    amount integer not null ,\n" +
                "    branchName varchar(50) not null,\n" +
                "    userNational_Code VARCHAR (50) not null,\n" +
                "    CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(userNational_Code) REFERENCES users(national_code) )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAcc(String username,String password,String branchName,String userId){

    }

}
