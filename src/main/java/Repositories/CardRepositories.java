package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public CardRepositories(){
        String sql="create table if not exists creditCard(cardId varchar(50) primary key,cvv2 varchar(50) not null ,password varchar(50) not null,expireDate DATE not null ,status ACC_STATUS not null,accId varchar ,\n" +
                "                                      CONSTRAINT fk_customer\n" +
                "                                          FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
