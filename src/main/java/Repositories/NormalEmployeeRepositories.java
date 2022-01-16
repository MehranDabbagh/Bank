package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NormalEmployeeRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public NormalEmployeeRepositories(){
        String sql="create table if not exists noramlEmployee(national_code varchar(50),jobtype EMPLOYEE_TYPE,branchName varchar(50),\n" +
                "    CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(branchName) REFERENCES bank(branchName))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
