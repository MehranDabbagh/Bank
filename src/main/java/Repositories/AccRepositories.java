package Repositories;



import Entities.Account;
import Entities.Status;
import Entities.User;
import MyConnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccRepositories implements CRUD <Account> {
    Connection connection=PostgresConnection.getInstance().getConnection();
    public AccRepositories() throws ClassNotFoundException, SQLException {
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
        String sqlTest ="select * from accs where accId=? ";
        String sql="insert into accs (accId,Status,password,amount,branchName,userNational_Code) values (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
            preparedStatement.setString(1,account.getAccId());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                account=new Account(account.getPassword(), account.getUserNationalCode(),account.getBranchName());
                 preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,account.getAccId());
                 resultSet=preparedStatement.executeQuery();
            }
            String sqlBranchTest="select from bank where branchName=?";
             preparedStatement=connection.prepareStatement(sqlBranchTest);
            preparedStatement.setString(1,account.getBranchName());
            ResultSet resultSet1=preparedStatement.executeQuery();
            if(resultSet1.next()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, account.getAccId());
                preparedStatement.setInt(2, 0);
                preparedStatement.setString(3, account.getPassword());
                preparedStatement.setInt(4, account.getAmount());
                preparedStatement.setString(5, account.getBranchName());
                preparedStatement.setString(6, account.getUserNationalCode());
                preparedStatement.execute();
                return account.getAccId();
            }else System.out.println("there is no branch with this name!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return "undefined!";
    }

    @Override
    public Account readById(String id) {
        String sql="select * from accs inner join users on accs.userNational_Code=users.national_code  where accId=? ";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
           ResultSet resultSet= preparedStatement.executeQuery();
           if(resultSet.next()){
                 Account account=new Account(resultSet.getString("password"),resultSet.getString("userNational_Code"),resultSet.getString("branchName"),resultSet.getString("accId"));
                 account.setAmount(resultSet.getInt("amount"));
                 account.setFoul(resultSet.getInt("foul"));
              return account;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return null;
    }

    @Override
    public List<Account> readAll() {
        String sql="select * from accs inner join users on accs.userNational_Code=users.national_code ";
        ArrayList <Account> finalACcs=new ArrayList<Account>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                Account account=new Account(resultSet.getString("password"),resultSet.getString("national_code"),resultSet.getString("branchName"));
                account.setAccId(resultSet.getString("accId"));
                account.setAmount(resultSet.getInt("amount"));
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

               String sqlTest="update accs set password=? ,amount=? ,status=? ,foul=? where accId=?";
                preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,account.getPassword());
                preparedStatement.setInt(2,account.getAmount());
                preparedStatement.setString(3,String.valueOf(account.getStatus()));
                preparedStatement.setInt(4,account.getFoul());
                preparedStatement.setString(5,account.getAccId());

preparedStatement.execute();
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
