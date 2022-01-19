package Repositories;

import Entities.Employee;
import Entities.JobType;
import MyConnection.PostgresConnection;

import java.sql.*;

public class ManagerRepositories {
    Connection connection=PostgresConnection.getInstance().getConnection();
    public ManagerRepositories() throws ClassNotFoundException, SQLException {
        String sql="create table if not exists manager(national_code varchar(50),jobtype varchar(50),branchName varchar(50) unique ,\n" +
                "                                          CONSTRAINT fk_customer\n" +
                "                                              FOREIGN KEY(branchName) REFERENCES bank(branchName))";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void create(String national_code,String branchName){
        String sqlCodeTest="select * from manager where national_code=? ";
        String sqlBranchTest="select * from manager where branchName=?";
        String sql="insert into manager (national_code,jobtype,branchName) values (?,?,?) ";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlCodeTest);
            preparedStatement.setString(1,national_code);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("there is a manager with this national code!");
            }else {
                preparedStatement=connection.prepareStatement(sqlBranchTest);
                preparedStatement.setString(1,branchName);
                ResultSet resultSet1= preparedStatement.executeQuery();
                if(resultSet1.next()){
                    System.out.println("this branch already have a manager!");
                }else {
             preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,national_code);
            preparedStatement.setString(2, String.valueOf(JobType.MANAGER));
            preparedStatement.setString(3, branchName);
            preparedStatement.execute();}}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
