package Repositories;

import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NormalEmployeeRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public NormalEmployeeRepositories(){
        String sql="create table if not exists noramlEmployee(national_code varchar(50),jobtype EMPLOYEE_TYPE,manager varchar(50) not null ,\n" +
                "    CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(manager) REFERENCES bank(manager))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
