package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransactionRepositories(){
        String sql="create table if not exists transactions(transactionId serial primary key ,transactionType transaction_type,amount INTEGER,accId INTEGER,\n" +
                "                                        CONSTRAINT fk_customer\n" +
                "                                            FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
