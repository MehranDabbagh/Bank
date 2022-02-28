package Services;

import Entities.*;
import Repositories.AccRepositories;
import Repositories.CardRepositories;
import Repositories.TransactionRepositories;
import Repositories.TransferRepositories;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.*;
import java.util.stream.Collectors;

public class AccServices  {
    private AccRepositories accRepositories=new AccRepositories();
    private Account loggedInAcc;
    private TransferRepositories transferRepositories=new TransferRepositories();
    private TransactionRepositories transactionRepositories=new TransactionRepositories();
    private CardRepositories cardRepositories=new CardRepositories();



    public Account login(String AccId,String password){
       Account account= accRepositories.readById(Long.valueOf(AccId));
       try{ if (Objects.equals(account.getPassword(), password) && account.getFoul()<3) {
           System.out.println("welcome " + account.getUserNationalCode() + "!");
           loggedInAcc = account;
           return loggedInAcc;
       } else if (Objects.equals(account.getPassword(), "?")) {
           System.out.println("please initialize your password first!");
           return null;
       } else if( account.getFoul()>=3) {
           System.out.println("your acc have been banned!");
           return null;
       }else{
           loggedInAcc = account;
           loggedInAcc.setFoul(loggedInAcc.getFoul() + 1);
           System.out.println(loggedInAcc.getFoul());
           accRepositories.update(loggedInAcc);
           return null;
       }
       }catch (NullPointerException e){
           System.out.println("there is no acc with this id!");
       }
        return null;
    }
    public String create(User user,String password) {
        Account account=new Account();
        String accId=String.valueOf(accRepositories.create(account));
        return accId;
    }
    public void read() {
       Account account= accRepositories.readById(Long.valueOf(loggedInAcc.getAccId()));
       System.out.println("acc id: "+account.getAccId()+" amount: "+account.getAmount()+ " branch: "+account.getBranchName()+" owner: "+account.getUserNationalCode());
       }
    public void update(String password) {
        try {
            Account test = accRepositories.readById(Long.valueOf(loggedInAcc.getAccId()));
                test.setPassword(password);
                accRepositories.update(test);
        }catch (NullPointerException e){

        }
    }
    public void delete() {
       accRepositories.delete(loggedInAcc);
       loggedInAcc=null;
    }
    public void showingUserAccounts(String nationalCode){
       List<Account> accounts=accRepositories.readAll();
       try {
           for (Account account : accounts
           ) {
               if (Objects.equals(account.getUserNationalCode(), nationalCode)) {
                   System.out.println("acc id: " + account.getAccId() + " amount: " + account.getAmount() + " branch: " + account.getBranchName() + " owner: " + account.getUserNationalCode());
               }

           }
       }catch (NullPointerException e){

       }
    }
     public void showingTransactionAndTransfersSinceNow(Date date,String accId) {
        try {
            java.sql.Date date1 = new java.sql.Date(date.getTime());
            Date date2 = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date2);
            List<Transfer> transfers = transferRepositories.readAll();
            List<Transaction> transactions = transactionRepositories.readAll();
            List<CreditCard> creditCards = cardRepositories.readAll();
            CreditCard currentCard = creditCards.get(0);
            for (CreditCard creditcard : creditCards
            ) {
                if (Objects.equals(creditcard.getAccId(), accId)) {
                    currentCard = creditcard;
                }
            }

            try {
                for (Transfer transfer : transfers
                ) {


                    if (cal1.compareTo(cal) > 0) {
                        if (Objects.equals(transfer.getSenderCardId(), currentCard.getCardId()) || Objects.equals(transfer.getReceiverCardId(), currentCard.getCardId())) {

                            System.out.println("sender Id: " + transfer.getSenderCardId() + " receiver Id :" + transfer.getSenderCardId() + " amount: " + transfer.getAmount() + " In:" + transfer.getDate());

                        }
                    }
                }
            }catch (NullPointerException e){

            }
            try {
                for (Transaction transaction : transactions
                ) {


                    if (cal1.compareTo(cal) > 0) {
                        if (Objects.equals(transaction.getAccId(), accId)) {

                            System.out.println("Acc Id: " + transaction.getAccId() + " amount: " + transaction.getAmount() + " In:" + transaction.getDate() + " operation type:" + transaction.getTransactionType());

                        }
                    }
                }
            }catch (NullPointerException e){}

        }catch (NullPointerException e){

        }
     }
     public void logout(){
        this.loggedInAcc=null;
     }
}
