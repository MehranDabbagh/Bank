package Repositories;

import Entities.CreditCard;
import Entities.Status;
import MyConnection.PostgresConnection;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardRepositories implements CRUD <CreditCard> {
    Connection connection= PostgresConnection.getInstance().getConnection();
    public CardRepositories(){
        String sql="create table if not exists creditCard(cardId varchar(50) primary key,cvv2 varchar(50) not null ,password varchar(50) not null,expireDate DATE not null ,status ACC_STATUS not null,accId varchar ,foul integer not null,\n" +
                "                                      CONSTRAINT fk_customer\n" +
                "                                          FOREIGN KEY(accId) REFERENCES accs(accId))";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(CreditCard creditCard) throws SQLException {
        String sqlTest="select * from creditCard where cardId=?  ";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlTest);
        preparedStatement.setString(1,creditCard.getCardId());
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
           creditCard=new CreditCard(creditCard.getPassword(),creditCard.getAccount());
            preparedStatement.setString(1,creditCard.getCardId());
             resultSet=preparedStatement.executeQuery();
        }
        String sql="insert into creditCard (cardId,cvv2,password,expireDate,status,accId) values (?,?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,creditCard.getCardId());
        preparedStatement.setString(2,creditCard.getCvv2());
        preparedStatement.setString(3,creditCard.getPassword());
        Date date=creditCard.getExpireDate();
        java.sql.Date date1=new  java.sql.Date(date.getTime());
        preparedStatement.setDate(4,date1);
        preparedStatement.setString(5,String.valueOf(creditCard.getStatus()));
        preparedStatement.setString(5,creditCard.getAccount().getAccId());
        preparedStatement.execute();
        return creditCard.getCardId();
    }

    @Override
    public CreditCard readById(String id) throws SQLException {
          String sql="select * from creditCard inner join accs where cardId=?";
          PreparedStatement preparedStatement=connection.prepareStatement(sql);
          preparedStatement.setString(1,id);
          ResultSet resultSet=preparedStatement.executeQuery();
          if(resultSet.next()){

              java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
              CreditCard creditCard=new CreditCard(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),newDate, Status.valueOf(resultSet.getString(5)));
              return creditCard;
          }
          return null;
    }

    @Override
    public List<CreditCard> readAll() throws SQLException {
        String sql="select * from creditCard inner join accs";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ArrayList<CreditCard> creditCards=new ArrayList<CreditCard>();
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
            CreditCard creditCard=new CreditCard(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),newDate, Status.valueOf(resultSet.getString(5)));
            creditCards.add(creditCard);
        }
        return creditCards;
    }

    @Override
    public Integer update(CreditCard creditCard) throws SQLException {
        String sql="select * from creditCard where cardId=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,creditCard.getCardId());
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                String sqlTest="update accs set cvv2=? , password=? ,expireDate=? ,status=?,foul=? where cardId=?";
                preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,creditCard.getCvv2());
                preparedStatement.setString(2,creditCard.getPassword());
                Date date=creditCard.getExpireDate();
                java.sql.Date date1=new  java.sql.Date(date.getTime());
                preparedStatement.setDate(3,date1);
                preparedStatement.setString(4,String.valueOf(creditCard.getStatus()));
                preparedStatement.setInt(5,creditCard.getFoul());
                preparedStatement.setString(6,creditCard.getAccount().getAccId());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public Integer delete(String id) throws SQLException {
        String sql ="delete from creditCard where cardId=?";
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
