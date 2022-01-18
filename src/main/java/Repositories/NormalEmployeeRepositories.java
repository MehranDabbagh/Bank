package Repositories;

import Entities.JobType;
import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void create(String national_code, String branchName){
        String sql2="select * from manager where branchName=? where exists (select * from bank where branchName=?)";
        String sql="insert into noramlEmployee (national_code,jobtype,manager) values (?,?,?))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.setString(1,branchName);
            preparedStatement.setString(2,branchName);
           ResultSet resultSet= preparedStatement.executeQuery();
         if(resultSet.next()) {
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, national_code);
             preparedStatement.setString(2, String.valueOf(JobType.NORMAL));
             preparedStatement.setString(3, resultSet.getString("1"));
         }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
