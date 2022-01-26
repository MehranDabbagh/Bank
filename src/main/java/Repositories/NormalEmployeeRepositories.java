package Repositories;

import Entities.JobType;
import MyConnection.PostgresConnection;

import java.sql.*;

public class NormalEmployeeRepositories {
    Connection connection=PostgresConnection.getInstance().getConnection();

    public void create(String national_code, String branchName){
        String sql2="select * from manager where branchName=? ";
        String sql="insert into noramlEmployee (national_code,jobtype,branchName) values (?,?,?)";
        String sql3="select * from noramlEmployee where national_code=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.setString(1,branchName);
           ResultSet resultSet= preparedStatement.executeQuery();
         if(resultSet.next()) {
             preparedStatement= connection.prepareStatement(sql3);
             preparedStatement.setString(1,national_code);
             ResultSet resultSet1=preparedStatement.executeQuery();
             if(resultSet1.next()){
                 System.out.println("there is a employee with this national code");
             }else {
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, national_code);
             preparedStatement.setString(2, String.valueOf(JobType.NORMAL));
             preparedStatement.setString(3, branchName);
        preparedStatement.execute();}
         }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
