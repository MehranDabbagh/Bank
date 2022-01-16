package Repositories;

import JdbcConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public ManagerRepositories() throws SQLException {
        String sql="create table if not exists manager(national_code varchar(50),jobtype EMPLOYEE_TYPE,branchName varchar(50) unique ,\n" +
                "                                          CONSTRAINT fk_customer\n" +
                "                                              FOREIGN KEY(branchName) REFERENCES bank(branchName))";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.execute();
    }
}
