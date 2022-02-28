package Repositories;

import Entities.*;
import MyConnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransactionRepositories implements CRUD<Transaction,String> {
    Connection connection=PostgresConnection.getInstance().getConnection();
    @Override
    public String create(Transaction transaction) {
        try{
        String sqlTest="select * from transactions where  transactionId=?  ";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
        preparedStatement.setString(1,transaction.getTransactionId());
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            transaction=new Transaction(transaction.getTransactionId(),transaction.getTransactionType(),transaction.getAmount(),transaction.getAccId(),transaction.getDate());
            preparedStatement.setString(1,transaction.getTransactionId());
            resultSet=preparedStatement.executeQuery();
        }
        String sql="insert into transactions (transactionId,transactionType,amount,accId,transactionDate ) values (?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, transaction.getTransactionId());
        preparedStatement.setString(2,String.valueOf(transaction.getTransactionType()));
        preparedStatement.setInt(3,transaction.getAmount());
        preparedStatement.setString(4,transaction.getAccId());
        Date date=transaction.getDate();
        java.sql.Date date1=new  java.sql.Date(date.getTime());
        preparedStatement.setDate(5,date1);
        preparedStatement.execute();
        return transaction.getTransactionId();
        }catch (SQLException e ){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }return "";
    }

    @Override
    public Transaction readById(String id) {
        try{
        String sql="select * from accs inner join transactions on accs.accid=transactions.accid inner join users on accs.usernational_code=users.national_code where transactionid=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            Account account=new Account(resultSet.getString("password"),resultSet.getString("national_code"),resultSet.getString("branchName"));
            java.util.Date newDate = new Date(resultSet.getDate("date").getTime());
            TransactionType transactionType;
            if(Objects.equals(resultSet.getString(2), "WITHDREW")){
                transactionType=TransactionType.WITHDREW;
            }else if(Objects.equals(resultSet.getString(2), "DEPOSIT")){
                transactionType=TransactionType.DEPOSIT;
            }else  if(Objects.equals(resultSet.getString(2), "TRANSFER")){
                transactionType=TransactionType.TRANSFER;
            }else transactionType=TransactionType.WITHDREW;
            Transaction transaction=new Transaction(resultSet.getString(1),transactionType,resultSet.getInt(3), account.getAccId(),newDate);

            return transaction;
        }
        }catch (SQLException e ){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> readAll()  {
        try {
            String sql = "select * from accs inner join transactions on accs.accid=transactions.accid inner join users on accs.usernational_code=users.national_code";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            while (resultSet.next()) {
                String sql1 = "select * from transactions where transactionid=?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, resultSet.getString("transactionid"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                resultSet1.next();
                Account account = new Account(resultSet.getString("password"), resultSet.getString("national_code"), resultSet.getString("branchName"));
                java.util.Date newDate = new Date(resultSet.getDate("transactiondate").getTime());
                TransactionType transactionType;
                if (Objects.equals(resultSet.getString("transactiontype"), "WITHDREW")) {
                    transactionType = TransactionType.WITHDREW;
                } else if (Objects.equals(resultSet.getString("transactiontype"), "DEPOSIT")) {
                    transactionType = TransactionType.DEPOSIT;
                } else if (Objects.equals(resultSet.getString("transactiontype"), "TRANSFER")) {
                    transactionType = TransactionType.TRANSFER;
                } else transactionType = TransactionType.DEPOSIT;
                Transaction transaction = new Transaction(resultSet.getString("transactionid"), transactionType, resultSet1.getInt("amount"), resultSet.getString("accid"), newDate);
                transactions.add(transaction);
            }
            if (transactions.size() > 0) {
                return transactions;
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Integer update(Transaction transaction) {
        return null;
    }

    @Override
    public Integer delete(String id)  {
        return null;
    }
}
