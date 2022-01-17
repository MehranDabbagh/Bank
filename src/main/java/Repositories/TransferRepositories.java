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

public class TransferRepositories implements CRUD<Transfer> {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public TransferRepositories(){
        String sql="create table if not exists transfer(transferId varchar(50) primary key ,amount INTEGER not null,senderId varchar(50) not null,reciverId varchar(50) not null,transferDate DATE NOT NULL  , CONSTRAINT fk_customer\n" +
                "    FOREIGN KEY(senderId) REFERENCES accs(accId),\n" +
                "    FOREIGN KEY(reciverId) REFERENCES accs(accId)\n" +
                "                                   )";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String create(Transfer transfer) throws SQLException {
        String sqlTest="select * from transfer where  transferId=?  ";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
        preparedStatement.setString(1,transfer.getTransferId());
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            transfer=new Transfer(transfer.getSenderCard(),transfer.getReceiverCard(),transfer.getAmount(),transfer.getDate());
            preparedStatement.setString(1,transfer.getTransferId());
            resultSet=preparedStatement.executeQuery();
        }
        String sql="insert into transfer (transferId,amount,senderId,reciverId,transferDate ) values (?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, transfer.getTransferId());
        preparedStatement.setInt(2,transfer.getAmount());
        preparedStatement.setString(3,transfer.getSenderCard().getCardId());
        preparedStatement.setString(4,transfer.getReceiverCard().getCardId());
        Date date=transfer.getDate();
        java.sql.Date date1=new  java.sql.Date(date.getTime());
        preparedStatement.setDate(5,date1);
        preparedStatement.execute();
        return transfer.getTransferId();

    }

    @Override
    public Transfer readById(String id) throws SQLException {
        String sql="select * from transfer  where transactionId=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            String sql1="select * from accs inner join users inner join creditCard where  accId=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,resultSet.getString("senderId"));
            ResultSet resultSet1 =preparedStatement.executeQuery();
            Account account1=new Account(resultSet1.getString("password"),resultSet1.getString("branchName"));
            User user1=new User(resultSet1.getString("national_code"),resultSet1.getString("(users).password"),account1);
            account1=new Account(resultSet1.getString("password"),resultSet1.getString("accId"),resultSet1.getString("branchName"),resultSet1.getInt("amount"), Status.valueOf(resultSet1.getString("status")),user1);
             CreditCard creditCard1=new CreditCard(resultSet1.getString("(creditcard).password"),account1);
            java.util.Date newDate = new Date(resultSet.getDate("date").getTime());
            String sql2="select * from accs inner join users inner join creditCard where accId=?";
            preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.setString(1,resultSet.getString("reciverId"));
            ResultSet resultSet2 =preparedStatement.executeQuery();
            Account account2=new Account(resultSet2.getString("password"),resultSet2.getString("branchName"));
            User user2=new User(resultSet2.getString("national_code"),resultSet2.getString("(users).password"),account2);
            account2=new Account(resultSet2.getString("password"),resultSet2.getString("accId"),resultSet2.getString("branchName"),resultSet2.getInt("amount"), Status.valueOf(resultSet2.getString("status")),user2);
            CreditCard creditCard2=new CreditCard(resultSet2.getString("(creditcard).password"),account2);
            java.util.Date date1 = new Date(resultSet.getDate("date").getTime());
            Transfer transfer=new Transfer(creditCard1,creditCard2,resultSet.getInt("amount"),date1);
            return transfer;
        }


        return null;
    }

    @Override
    public List<Transfer> readAll() throws SQLException {

        String sql="select * from transfer";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ResultSet resultSet=preparedStatement.executeQuery();
        ArrayList<Transfer> transfers=new ArrayList<Transfer>();
        while(resultSet.next()){
            String sql1="select * from accs inner join users inner join creditCard where  accId=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setString(1,resultSet.getString("senderId"));
            ResultSet resultSet1 =preparedStatement.executeQuery();
            Account account1=new Account(resultSet1.getString("password"),resultSet1.getString("branchName"));
            User user1=new User(resultSet1.getString("national_code"),resultSet1.getString("(users).password"),account1);
            account1=new Account(resultSet1.getString("password"),resultSet1.getString("accId"),resultSet1.getString("branchName"),resultSet1.getInt("amount"), Status.valueOf(resultSet1.getString("status")),user1);
            CreditCard creditCard1=new CreditCard(resultSet1.getString("(creditcard).password"),account1);
            java.util.Date newDate = new Date(resultSet.getDate("date").getTime());
            String sql2="select * from accs inner join users inner join creditCard where accId=?";
            preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.setString(1,resultSet.getString("reciverId"));
            ResultSet resultSet2 =preparedStatement.executeQuery();
            Account account2=new Account(resultSet2.getString("password"),resultSet2.getString("branchName"));
            User user2=new User(resultSet2.getString("national_code"),resultSet2.getString("(users).password"),account2);
            account2=new Account(resultSet2.getString("password"),resultSet2.getString("accId"),resultSet2.getString("branchName"),resultSet2.getInt("amount"), Status.valueOf(resultSet2.getString("status")),user2);
            CreditCard creditCard2=new CreditCard(resultSet2.getString("(creditcard).password"),account2);
            java.util.Date date1 = new Date(resultSet.getDate("date").getTime());
            Transfer transfer=new Transfer(creditCard1,creditCard2,resultSet.getInt("amount"),date1);
           transfers.add(transfer);
        }
if(transfers.size()>0){return transfers;}

        return null;
    }

    @Override
    public Integer update(Transfer transfer) throws SQLException {
        return null;
    }

    @Override
    public Integer delete(String id) throws SQLException {
        return null;
    }
}
