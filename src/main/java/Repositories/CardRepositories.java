package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public CardRepositories(){
        String sql="create table if not exists creditCard(cardId serial primary key ,cvv2 varchar(50),password varchar(50),expireDate DATE ,status ACC_STATUS,accId INTEGER,\n" +
                "                                      CONSTRAINT fk_customer\n" +
                "                                          FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
