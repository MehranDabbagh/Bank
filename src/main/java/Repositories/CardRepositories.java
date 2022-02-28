package Repositories;

import Entities.CreditCard;
import Entities.Status;
import MyConnection.PostgresConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardRepositories implements CRUD <CreditCard,String> {
    Connection connection=PostgresConnection.getInstance().getConnection();


    @Override
    public String create(CreditCard creditCard)  {
try {
    String sqlTest = "select * from creditCard where cardId=?  ";
    PreparedStatement preparedStatement = connection.prepareStatement(sqlTest);
    preparedStatement.setString(1, creditCard.getCardId());
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        creditCard = new CreditCard(creditCard.getPassword(), creditCard.getAccId());
        preparedStatement = connection.prepareStatement(sqlTest);
        preparedStatement.setString(1, creditCard.getCardId());
        resultSet = preparedStatement.executeQuery();
    }
    sqlTest = "select * from creditCard where accid=?  ";
    preparedStatement = connection.prepareStatement(sqlTest);
    preparedStatement.setString(1, creditCard.getAccId());
    resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
        System.out.println("this acc already have a card!");
    } else {
        String sql = "insert into creditCard (cardId,cvv2,password,expireDate,status,accId,foul) values (?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, creditCard.getCardId());
        preparedStatement.setString(2, creditCard.getCvv2());
        preparedStatement.setString(3, creditCard.getPassword());
        Date date = creditCard.getExpireDate();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        preparedStatement.setDate(4, date1);
        preparedStatement.setString(5, String.valueOf(creditCard.getStatus()));
        preparedStatement.setString(6, creditCard.getAccId());
        preparedStatement.setInt(7, 0);
        preparedStatement.execute();
        return creditCard.getCardId();
    }
}catch (SQLException e ){
    e.printStackTrace();
}catch (NullPointerException e){
    e.printStackTrace();
}
        return "0";
    }

    @Override
    public CreditCard readById(String id)  {
        try {
            String sql = "select * from creditCard inner join accs on creditCard.accid=accs.accid where cardId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
                CreditCard creditCard1 = new CreditCard(resultSet.getString("cardId"), resultSet.getString("cvv2"), resultSet.getString("password"), newDate, Status.OPEN);

                CreditCard creditCard = new CreditCard(resultSet.getString("cardId"), resultSet.getString("cvv2"), resultSet.getString("password"), newDate, Status.OPEN);

                return creditCard;
            }
         }catch (SQLException e ){
        e.printStackTrace();
    } catch (NullPointerException e){
            e.printStackTrace();
        }
          return null;
    }

    @Override
    public List<CreditCard> readAll() {
        try {
            String sql = "select * from creditCard inner join accs on accs.accid=creditCard.accid ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.util.Date newDate = new Date(resultSet.getDate("expireDate").getTime());
                CreditCard creditCard = new CreditCard(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), newDate, Status.valueOf(resultSet.getString(5)));
                creditCard.setAccId(resultSet.getString("accid"));
                creditCard.setCvv2(resultSet.getString("cvv2"));
                creditCard.setPassword(resultSet.getString("password"));
                creditCard.setExpireDate(newDate);
                creditCards.add(creditCard);
            }
            return creditCards;
        }catch (SQLException e ){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(CreditCard creditCard) {

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

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    @Override
    public void delete(CreditCard creditCard) {
        String sql ="delete from creditCard where accid=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,creditCard.getCardId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }



    }
}
