package Repositories;

import Entities.CreditCard;
import Entities.Status;
import MyConnection.PostgresConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardRepositories implements CRUD <CreditCard> {
    Connection connection=PostgresConnection.getInstance().getConnection();
    public CardRepositories() throws ClassNotFoundException, SQLException {
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
           creditCard=new CreditCard(creditCard.getPassword(),creditCard.getAccId());
            preparedStatement=connection.prepareStatement(sqlTest);
            preparedStatement.setString(1,creditCard.getCardId());
             resultSet=preparedStatement.executeQuery();
        }
        sqlTest="select * from creditCard where accid=?  ";
         preparedStatement=connection.prepareStatement(sqlTest);
        preparedStatement.setString(1,creditCard.getAccId());
         resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("this acc already have a card!");
        }else {
        String sql="insert into creditCard (cardId,cvv2,password,expireDate,status,accId,foul) values (?,?,?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,creditCard.getCardId());
        preparedStatement.setString(2,creditCard.getCvv2());
        preparedStatement.setString(3,creditCard.getPassword());
        Date date=creditCard.getExpireDate();
        java.sql.Date date1=new  java.sql.Date(date.getTime());
        preparedStatement.setDate(4,date1);
        preparedStatement.setString(5,String.valueOf(creditCard.getStatus()));
        preparedStatement.setString(6,creditCard.getAccId());
        preparedStatement.setInt(7,0);
        preparedStatement.execute();
        return creditCard.getCardId();
        }
        return "0";
    }

    @Override
    public CreditCard readById(String id) throws SQLException {
          String sql="select * from creditCard inner join accs on creditCard.accid=accs.accid where cardId=?";
          PreparedStatement preparedStatement=connection.prepareStatement(sql);
          preparedStatement.setString(1,id);
          ResultSet resultSet=preparedStatement.executeQuery();

          if(resultSet.next()){

              java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
              CreditCard creditCard1=new CreditCard(resultSet.getString("cardId"),resultSet.getString("cvv2"),resultSet.getString("password"),newDate,Status.OPEN);

              CreditCard creditCard=new CreditCard(resultSet.getString("cardId"),resultSet.getString("cvv2"),resultSet.getString("password"),newDate,Status.OPEN);

              return creditCard;
          }
          return null;
    }

    @Override
    public List<CreditCard> readAll() throws SQLException {
        String sql="select * from creditCard inner join accs on accs.accid=creditCard.accid ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ArrayList<CreditCard> creditCards=new ArrayList<CreditCard>();
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
            CreditCard creditCard=new CreditCard(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),newDate, Status.valueOf(resultSet.getString(5)));
            creditCard.setAccId(resultSet.getString("accid"));
            creditCard.setCvv2(resultSet.getString("cvv2"));
            creditCard.setPassword(resultSet.getString("password"));
            creditCard.setExpireDate(newDate);
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

                String sqlTest="update creditCard set password=?  ,status=?,foul=? where cardId=?";
                preparedStatement=connection.prepareStatement(sqlTest);
                preparedStatement.setString(1,creditCard.getPassword());
                preparedStatement.setString(2,String.valueOf(creditCard.getStatus()));
                preparedStatement.setInt(3,creditCard.getFoul());
                preparedStatement.setString(4,creditCard.getCardId());
                preparedStatement.execute();
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public Integer delete(String id) throws SQLException {
        String sql ="delete from creditCard where accid=?";
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
