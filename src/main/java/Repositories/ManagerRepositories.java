package Repositories;

import Entities.Employee;
import Entities.JobType;
import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerRepositories {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public ManagerRepositories(){
        String sql="create table if not exists manager(national_code varchar(50),jobtype EMPLOYEE_TYPE,branchName varchar(50) unique ,\n" +
                "                                          CONSTRAINT fk_customer\n" +
                "                                              FOREIGN KEY(branchName) REFERENCES bank(branchName))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void create(String national_code,String branchName){
        String sql="insert into manager (national_code,jobtype,branchName) values (?,?,?) where not exists(select * from manager where branchName=?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,national_code);
            preparedStatement.setString(2, String.valueOf(JobType.MANAGER));
            preparedStatement.setString(3, branchName);
            preparedStatement.setString(4,branchName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
