package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransferRepositories(){
        String sql="create table if not exists transfer(transferId varchar(50) primary key ,amount INTEGER not null,senderId varchar(50) not null,reciverId varchar(50) not null , CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(senderId) REFERENCES accs(accId),\n" +
                "    FOREIGN KEY(reciverId) REFERENCES accs(accId)\n" +
                "                                   )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
