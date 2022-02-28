package Repositories;



import Entities.Account;
import Entities.Status;
import Entities.User;
import MyConnection.PostgresConnection;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccRepositories implements CRUD <Account,Long> {


    @Override
    public Long create(Account account) {

    }

    @Override
    public Account readById(Long id) {
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
        }catch (NullPointerException e){
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
        }catch (NullPointerException e){
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
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer delete(Long id) {
String sql ="delete from accs where accId=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
          return   preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }
}
