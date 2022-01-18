package Services;

import Entities.*;
import Repositories.AccRepositories;
import Repositories.TransactionRepositories;
import Repositories.TransferRepositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AccServices  {
    private AccRepositories accRepositories=new AccRepositories();
    private Account loggedInAcc;
    private TransferRepositories transferRepositories=new TransferRepositories();
    private TransactionRepositories transactionRepositories=new TransactionRepositories();
    public Account login(String AccId,String password){
       Account account= accRepositories.readById(AccId);
       if(account==null){
           System.out.println("acc not found!");
           return null;
       }else if(Objects.equals(account.getPassword(), password)){
           System.out.println("welcome "+account.getUser().getNationalCode()+"!");
           loggedInAcc=account;
           return loggedInAcc;
       }else if(Objects.equals(account.getPassword(), "?")){
           System.out.println("please initialize your password first!");
           return null;
       } else {
           loggedInAcc=account;
           loggedInAcc.setFoul(loggedInAcc.getFoul()+1);
           return loggedInAcc;
       }

    }
    public String create(User user) {
        Account account=new Account(user);
        String accId=accRepositories.create(account);
        return accId;
    }
    public void read() {
       Account account= accRepositories.readById(loggedInAcc.getAccId());
       System.out.println("acc id: "+account.getAccId()+" amount: "+account.getAmount()+ " branch: "+account.getBranchName()+" owner: "+account.getUser().getNationalCode());
       }
    public void update(String password) {
     Account test=  accRepositories.readById(loggedInAcc.getAccId());
     if(password!=null){
                 test.setPassword(password);
                 accRepositories.update(test);
     } else System.out.println("please enter valid input!");
    }
    public void delete() {
       accRepositories.delete(loggedInAcc.getAccId());
       loggedInAcc=null;
    }
    public void showingUserAccounts(){
       List<Account> accounts=accRepositories.readAll();
        for (Account account:accounts
             ) {
            if(account.getUser()==loggedInAcc.getUser()){
                System.out.println("acc id: "+account.getAccId()+" amount: "+account.getAmount()+ " branch: "+account.getBranchName()+" owner: "+account.getUser().getNationalCode());
            }

        }

    }
     public void showingTransactionAndTransfers(Date date) throws SQLException {
         java.sql.Date date1=new  java.sql.Date(date.getTime());
        List<Transfer> transfers= transferRepositories.readAll();
        List<Transaction> transactions= transactionRepositories.readAll();
         for (Transfer transfer:transfers
              ) {
             if(transfer.getDate()==date1){
                 System.out.println("sender Id: "+transfer.getSenderCard().getCardId()+" receiver Id :"+transfer.getReceiverCard().getCardId()+" amount: "+transfer.getAmount()+ " In:"+transfer.getDate());
             }
         }
         for (Transaction transaction :transactions
         ) {
             if(transaction.getDate()==date1){
                 System.out.println("Acc Id: "+ transaction.getAccount().getAccId()+" amount: "+ transaction.getAmount()+ " In:"+ transaction.getDate()+" operation type:"+transaction.getTransactionType());
             }
         }
     }
     public void showingTransactionAndTransfersSinceNow(Date date) throws SQLException{
         java.sql.Date date1=new  java.sql.Date(date.getTime());
         Date date2=new Date();
         int compare1=date1.compareTo(date2);
         List<Transfer> transfers= transferRepositories.readAll();
         List<Transaction> transactions= transactionRepositories.readAll();
         for (Transfer transfer:transfers
         ) {
             int compare2=transfer.getDate().compareTo(date2);
             if(compare1<0 && compare2>0 ){
                 System.out.println("sender Id: "+transfer.getSenderCard().getCardId()+" receiver Id :"+transfer.getReceiverCard().getCardId()+" amount: "+transfer.getAmount()+ " In:"+transfer.getDate());
             }
         }
         for (Transaction transaction :transactions
         ) {
             if(transaction.getDate()==date1){
                 System.out.println("Acc Id: "+ transaction.getAccount().getAccId()+" amount: "+ transaction.getAmount()+ " In:"+ transaction.getDate()+" operation type:"+transaction.getTransactionType());
             }
         }
     }
     public void logout(){
        this.loggedInAcc=null;
     }
}
