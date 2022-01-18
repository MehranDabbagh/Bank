package Services;

import Entities.Account;
import Entities.CreditCard;
import Entities.Status;
import Entities.Transfer;
import Repositories.CardRepositories;
import Repositories.TransferRepositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class creditCardServices {
    CardRepositories cardRepositories=new CardRepositories();
    private TransferRepositories transferRepositories=new TransferRepositories();
    private CreditCard loggedIn;
    public void create(Account account) throws SQLException {
        List<CreditCard> creditCards=cardRepositories.readAll();
        boolean condition=true;
        for (CreditCard creditcard:creditCards
             ) {
            if(creditcard.getAccount().getAccId()==account.getAccId()){
                condition=false;
            }
        }

        if(!condition){
            System.out.println("this account already have a credit card!");
            return;
        }else {
            CreditCard creditCard=new CreditCard("?",account);
            cardRepositories.create(creditCard);
        }
    }
    public Boolean login(String cardId,String password) throws SQLException {
       CreditCard creditCard= cardRepositories.readById(cardId);
       if( creditCard!=null && Objects.equals(creditCard.getPassword(), password)){
           loggedIn=creditCard;
           return true;
       } else if(creditCard!=null){
           creditCard.setFoul(creditCard.getFoul()+1);
           cardRepositories.update(creditCard);

       } return false;
    }
    public void read() throws SQLException {
            System.out.println("cardId "+loggedIn.getCardId()+" acc of the card: "+loggedIn.getAccount().getAccId()+ " cvv2: "+loggedIn.getCvv2()+" expire date: "+loggedIn.getExpireDate()+" status: "+loggedIn.getStatus());
    }
    public void update(Integer operator,String newValue) throws SQLException{
        CreditCard creditCard=cardRepositories.readById(loggedIn.getCardId());
        switch (operator){
            case 0:creditCard.setPassword(newValue);cardRepositories.update(creditCard);break;
            case 1:creditCard.setStatus(Status.valueOf(newValue));cardRepositories.update(creditCard);break;
        }
    }
    public void delete() throws SQLException {
        cardRepositories.delete(loggedIn.getCardId());
        loggedIn=null;
    }
    public void transfer(String receiverCardId,Integer amount) throws SQLException{
        if(receiverCardId.length()==16 || receiverCardId.length()==14){
            if(loggedIn.getAccount().getAmount()>amount+600){
                Date date = new Date();
                int compare=loggedIn.getExpireDate().compareTo(date);
                if(compare>0){
                  CreditCard receiverCard=cardRepositories.readById("receiverCardId");
                    Transfer transfer=new Transfer(loggedIn, receiverCard ,amount,date);
                    transferRepositories.create(transfer);
                    loggedIn.getAccount().setAmount( loggedIn.getAccount().getAmount()-(amount+600));
                    receiverCard.getAccount().setAmount( receiverCard.getAccount().getAmount()+amount);
                      cardRepositories.update(loggedIn);
                      cardRepositories.update(receiverCard);
                      return;
                }else {System.out.println("your card is not valid anymore!");return;}
            }else {System.out.println("you don't have enough money for this operation!");
            return;}
        }else System.out.println("receiver creditCard is not valid!");

    }
    public void transaction(Integer operator,Integer amount){
        switch (operator){
            case 0:{if(loggedIn.getAccount().getAmount()>=amount){
               loggedIn.getAccount().setAmount(loggedIn.getAccount().getAmount()-amount);
            }else {System.out.println("there is no enough money for withdrew!");return;}
            break;
            }
            case 1:loggedIn.getAccount().setAmount(loggedIn.getAccount().getAmount()+amount);
        }
    }
    public void transferWithoutLogin(String senderId,String password,String cvv2,String receiverCardId,Integer amount) throws SQLException {
      if( login(senderId,password)){
          if(Objects.equals(loggedIn.getCvv2(), cvv2)){
                transfer(receiverCardId,amount);
          }else System.out.println("cvv2 is wrong!"); return;
      } System.out.println("there is no card with this username and password");return;
    }
}
