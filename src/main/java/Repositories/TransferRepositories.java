package Repositories;

import JdbcConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransferRepositories() throws SQLException {
        String sql="create table if not exists transfer(transferId serial primary key ,amount INTEGER,senderId INTEGER,reciverId INTEGER, CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(senderId) REFERENCES accs(accId),\n" +
                "    FOREIGN KEY(reciverId) REFERENCES accs(accId)\n" +
                "                                   )";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.execute();
    }
}
