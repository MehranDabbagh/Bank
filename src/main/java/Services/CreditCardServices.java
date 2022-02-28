package Services;

import Entities.*;
import Repositories.AccRepositories;
import Repositories.CardRepositories;
import Repositories.TransactionRepositories;
import Repositories.TransferRepositories;

import java.sql.SQLException;
import java.util.*;

public class CreditCardServices {
    CardRepositories cardRepositories=new CardRepositories();
    private TransferRepositories transferRepositories=new TransferRepositories();
    private CreditCard loggedIn;
    private AccRepositories accRepositories=new AccRepositories();
    private TransactionRepositories transactionRepositories=new TransactionRepositories();


    public void create(Account account) {
    try {
        List<CreditCard> creditCards = cardRepositories.readAll();
        boolean condition = true;
        for (CreditCard creditcard : creditCards
        ) {
            if (Objects.equals(creditcard.getAccId(), account.getAccId())) {
                condition = false;
            }
        }

        if (!condition) {
            System.out.println("this account already have a credit card!");
            return;
        } else {
            CreditCard creditCard = new CreditCard("?", account.getAccId());
            cardRepositories.create(creditCard);
        }
    }catch (NullPointerException e){
            e.printStackTrace();
    }
    }
    public Boolean login(String cardId,String password)  {
       CreditCard creditCard= cardRepositories.readById(cardId);
       try {
           if (Objects.equals(creditCard.getPassword(), password)) {
               loggedIn = creditCard;
               return true;
           } else if (creditCard != null) {
               creditCard.setFoul(creditCard.getFoul() + 1);
               cardRepositories.update(creditCard);

           }
           return false;
       }catch (NullPointerException e){
           return false;
       }
    }
    public void read()  {
            System.out.println("cardId "+loggedIn.getCardId()+" acc of the card: "+loggedIn.getAccId()+ " cvv2: "+loggedIn.getCvv2()+" expire date: "+loggedIn.getExpireDate()+" status: "+loggedIn.getStatus());
    }
    public void update(String password) {
        try {
            CreditCard creditCard = cardRepositories.readById(loggedIn.getCardId());
            creditCard.setPassword(password);
            cardRepositories.update(creditCard);
        }catch (NullPointerException e){

        }
        }
    public void delete(Account account)  {
        cardRepositories.delete(cardRepositories.readById(account.getAccId()));
        loggedIn=null;
    }
    public void transfer(String SenderCardId,String receiverCardId,String cvv2,String password,Date expireDate,Integer amount){
      List<CreditCard>  creditCards=cardRepositories.readAll();
      CreditCard senderCard =creditCards.get(0);
      CreditCard receiverCard=creditCards.get(0);
      try {
          for (CreditCard creditCard1 : creditCards
          ) {
              if (Objects.equals(creditCard1.getCardId(), SenderCardId)) {
                  senderCard = creditCard1;
              }
          }
          for (CreditCard creditCard2 : creditCards
          ) {
              if (Objects.equals(creditCard2.getCardId(), receiverCardId)) {
                  receiverCard = creditCard2;
              }
          }
          List<Account> accounts = accRepositories.readAll();
          Account senderAcc = accounts.get(0);
          for (Account account2 : accounts
          ) {

              if (Objects.equals(account2.getAccId(), senderCard.getAccId())) {

                  senderAcc = account2;
              }
          }
          Account receiverAcc = accounts.get(0);
          for (Account account3 : accounts
          ) {
              if (Objects.equals(account3.getAccId(), receiverCard.getAccId())) {
                  receiverAcc = account3;
              }
          }

          if (SenderCardId.length() == 10 && receiverCardId.length() == 10) {
              if (cvv2.length() == 4) {

                  if (senderAcc.getAmount() > amount + 600) {
                      Date date2 = new Date();
                      Calendar cal = Calendar.getInstance();
                      cal.setTime(senderCard.getExpireDate());
                      Calendar cal1 = Calendar.getInstance();
                      cal1.setTime(date2);
                      if (cal1.compareTo(cal) < 0 || expireDate == senderCard.getExpireDate()) {

                          Transfer transfer = new Transfer(senderCard.getCardId(), receiverCard.getCardId(), amount, date2);
                          transferRepositories.create(transfer);
                          senderAcc.setAmount(senderAcc.getAmount() - (amount + 600));
                          receiverAcc.setAmount(receiverAcc.getAmount() + amount);
                          if (Objects.equals(senderCard.getCardId(), SenderCardId) && Objects.equals(senderCard.getPassword(), password) && Objects.equals(senderCard.getCvv2(), cvv2)) {
                              accRepositories.update(senderAcc);
                              accRepositories.update(receiverAcc);
                          } else System.out.println("wrong details!");

                          return;
                      } else {
                          System.out.println("your card is not valid anymore!");
                          return;
                      }
                  } else {
                      System.out.println("you don't have enough money for this operation!");
                      return;
                  }
              } else System.out.println("receiver creditCard is not valid!");
          } else System.out.println("receiver creditCard is not valid!");
      }catch (NullPointerException e){
          e.printStackTrace();
      }
    }
    public void transaction(Integer operator,Integer amount,Account account)  {
        Account loggedInAcc = accRepositories.readById(account.getAccId());
        try {
            switch (operator) {
                case 0: {
                    if (loggedInAcc.getAmount() >= amount) {
                        loggedInAcc.setAmount(loggedInAcc.getAmount() - amount);
                        accRepositories.update(loggedInAcc);
                        Date date2 = new Date();
                        Transaction transaction = new Transaction(TransactionType.WITHDREW, amount, loggedInAcc.getAccId(), date2);
                        transactionRepositories.create(transaction);
                    } else {
                        System.out.println("there is no enough money for withdrew!");
                        return;
                    }
                    break;
                }
                case 1:
                    loggedInAcc.setAmount(loggedInAcc.getAmount() + amount);
                    accRepositories.update(loggedInAcc);
                    Date date2 = new Date();
                    Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, loggedInAcc.getAccId(), date2);
                    transactionRepositories.create(transaction);
            }
        }catch (NullPointerException e){

        }
    }
    public void initialize(String cardId) {
        try {
            List<CreditCard> creditCards = cardRepositories.readAll();
            Scanner input = new Scanner(System.in);
            for (CreditCard creditcard : creditCards
            ) {
                if (Objects.equals(creditcard.getCardId(), cardId)) {
                    if (Objects.equals(creditcard.getPassword(), "?")) {
                        System.out.println("please initialize your password");
                        String password = input.next();
                        loggedIn = creditcard;
                        update(password);
                    } else {
                        System.out.println("please enter your old password");
                        ;
                        String password = input.next();
                        if (Objects.equals(creditcard.getPassword(), password)) {
                            System.out.println("please enter your new  password");
                            ;
                            password = input.next();
                            loggedIn = creditcard;
                            update(password);
                        } else System.out.println("wrong!");
                    }
                }

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
