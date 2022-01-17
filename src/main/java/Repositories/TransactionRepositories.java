package Repositories;

import Entities.*;
import MyConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionRepositories implements CRUD<Transaction> {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransactionRepositories(){
        String sql="create table if not exists transactions(transactionId varchar (50) primary key ,transactionType transaction_type not null,amount INTEGER  not null,accId varchar(50)  not null ,transactionDate DATE ,\n" +
                "                                        CONSTRAINT fk_customer\n" +
                "                                            FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Transaction transaction) throws SQLException {
        String sqlTest="select * from transactions where  transactionId=?  ";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
        preparedStatement.setString(1,transaction.getTransactionId());
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            transaction=new Transaction(transaction.getTransactionId(),transaction.getTransactionType(),transaction.getAmount(),transaction.getAccount(),transaction.getDate());
            preparedStatement.setString(1,transaction.getTransactionId());
            resultSet=preparedStatement.executeQuery();
        }
        String sql="insert into transactions (transactionId,transactionType,amount,accId,transactionDate ) values (?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, transaction.getTransactionId());
        preparedStatement.setString(2,String.valueOf(transaction.getTransactionType()));
        preparedStatement.setInt(3,transaction.getAmount());
        preparedStatement.setString(4,transaction.getAccount().getAccId());
        Date date=transaction.getDate();
        java.sql.Date date1=new  java.sql.Date(date.getTime());
        preparedStatement.setDate(5,date1);
        preparedStatement.execute();
        return transaction.getTransactionId();

    }

    @Override
    public Transaction readById(String id) throws SQLException {
        String sql="select * from transactions inner join accs inner join users where transactionId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            Account account=new Account(resultSet.getString("password"),resultSet.getString("branchName"));
            User user=new User(resultSet.getString("national_code"),resultSet.getString("(users).password"),account);
            account=new Account(resultSet.getString("password"),resultSet.getString("accId"),resultSet.getString("branchName"),resultSet.getInt("amount"), Status.valueOf(resultSet.getString("status")),user);
            java.util.Date newDate = new Date(resultSet.getDate("date").getTime());
          Transaction transaction=new Transaction(resultSet.getString(1), TransactionType.valueOf(resultSet.getString(2)),resultSet.getInt(3), account,newDate);
           return transaction;
        }

        return null;
    }

    @Override
    public List<Transaction> readAll() throws SQLException {
        String sql="select * from transactions inner join accs inner join users ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        ArrayList<Transaction> transactions=new ArrayList<Transaction>();
        if(resultSet.next()){
            Account account=new Account(resultSet.getString("password"),resultSet.getString("branchName"));
            User user=new User(resultSet.getString("national_code"),resultSet.getString("(users).password"),account);
            account=new Account(resultSet.getString("password"),resultSet.getString("accId"),resultSet.getString("branchName"),resultSet.getInt("amount"), Status.valueOf(resultSet.getString("status")),user);
            java.util.Date newDate = new Date(resultSet.getDate("date").getTime());
            Transaction transaction=new Transaction(resultSet.getString(1), TransactionType.valueOf(resultSet.getString(2)),resultSet.getInt(3), account,newDate);
            transactions.add(transaction);
        }
       if(transactions.size()>0) {return transactions;}
        return null;

    }

    @Override
    public Integer update(Transaction transaction) throws SQLException {
        return null;
    }

    @Override
    public Integer delete(String id) throws SQLException {
        return null;
    }
}
