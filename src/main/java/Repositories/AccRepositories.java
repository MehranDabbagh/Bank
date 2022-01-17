package Repositories;



import Entities.Account;
import Entities.Status;
import Entities.User;
import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccRepositories implements CRUD <Account> {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public AccRepositories(){
        String sql="create table if not exists accs(accId varchar(50) primary key,Status acc_status not null,password varchar(50) not null,\n" +
                "    amount integer not null ,\n" +
                "    branchName varchar(50) not null,foul INTEGER ,\n" +
                "    userNational_Code VARCHAR (50) not null,\n" +
                "    CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(userNational_Code) REFERENCES users(national_code) )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Account account) {
        String sqlTest ="select * from accs where id=? ";
        String sql="insert into accs (accId,Status,password,amount,branchName,userNationalCode) values (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
            preparedStatement.setString(1,account.getAccId());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                account=new Account(account.getPassword(), account.getBranchName(), account.getUser());
                 preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,account.getAccId());
                 resultSet=preparedStatement.executeQuery();
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,account.getAccId());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setInt(3,account.getAmount());
            preparedStatement.setString(4,account.getBranchName());
            preparedStatement.setString(5,account.getUser().getNationalCode());
            preparedStatement.execute();
            return account.getAccId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return "undefined!";
    }

    @Override
    public Account readById(String id) {
        String sql="select * from accs inner join users where accId=? ";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
           ResultSet resultSet= preparedStatement.executeQuery();
           if(resultSet.next()){
                 Account account=new Account(resultSet.getString("password"),resultSet.getString("branchName"));
                 User user=new User(resultSet.getString("national_code"),resultSet.getString("(users).password"),account);
                 account=new Account(resultSet.getString("password"),resultSet.getString("accId"),resultSet.getString("branchName"),resultSet.getInt("amount"), Status.valueOf(resultSet.getString("status")),user);
                 return account;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return null;
    }

    @Override
    public List<Account> readAll() {
        String sql="select * from accs inner join users ";
        ArrayList <Account> finalACcs=new ArrayList<Account>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);

            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                Account account=new Account(resultSet.getString("password"),resultSet.getString("branchName"));
                User user=new User(resultSet.getString("national_code"),resultSet.getString("(users).password"),account);
                account=new Account(resultSet.getString("password"),resultSet.getString("accId"),resultSet.getString("branchName"),resultSet.getInt("amount"), Status.valueOf(resultSet.getString("status")),user);
               finalACcs.add(account);
            }
            if(finalACcs.size()>0){
                return finalACcs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Account account) {
        String sql="select * from accs where accId=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
      preparedStatement.setString(1,account.getAccId());
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
               String sqlTest="update accs set status=? , password=? ,amount=? ,branchName=?,foul=?,userNational_Code=? where accId=?";
                preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,String.valueOf(account.getStatus()));
                preparedStatement.setString(2,account.getPassword());
                preparedStatement.setInt(3,account.getAmount());
                preparedStatement.setString(4,account.getBranchName());
                preparedStatement.setInt(5,account.getFoul());
                preparedStatement.setString(6,account.getUser().getNationalCode());
                preparedStatement.setString(7,account.getAccId());

                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer delete(String id) {
String sql ="delete from accs where accId=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
          return   preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
