package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransactionRepositories(){
        String sql="create table if not exists transactions(transactionId varchar (50) primary key ,transactionType transaction_type not null,amount INTEGER  not null,accId varchar(50)  not null ,\n" +
                "                                        CONSTRAINT fk_customer\n" +
                "                                            FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
