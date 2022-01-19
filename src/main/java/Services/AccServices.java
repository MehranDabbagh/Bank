package Services;

import Entities.*;
import Repositories.AccRepositories;
import Repositories.TransactionRepositories;
import Repositories.TransferRepositories;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AccServices  {
    private AccRepositories accRepositories=new AccRepositories();
    private Account loggedInAcc;
    private TransferRepositories transferRepositories=new TransferRepositories();
    private TransactionRepositories transactionRepositories=new TransactionRepositories();

    public AccServices() throws SQLException, ClassNotFoundException {
    }

    public Account login(String AccId,String password){
       Account account= accRepositories.readById(AccId);
       if(account==null){
           System.out.println("acc not found!");
           return null;
       }else if(Objects.equals(account.getPassword(), password)){
           System.out.println("welcome "+account.getUserNationalCode()+"!");
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
    public String create(User user,String password) {
        Account account=new Account(password,user.getNationalCode(),user.getBranchName());
        String accId=accRepositories.create(account);
        return accId;
    }
    public void read() {
       Account account= accRepositories.readById(loggedInAcc.getAccId());
       System.out.println("acc id: "+account.getAccId()+" amount: "+account.getAmount()+ " branch: "+account.getBranchName()+" owner: "+account.getUserNationalCode());
       }
    public void update(String password) {
     Account test=  accRepositories.readById(loggedInAcc.getAccId());
     if(password!=null){

                 test.setPassword(password);
                 accRepositories.update(test);
     } else if(test==null){ System.out.println("salam");}else System.out.println("please enter valid input!");
    }
    public void delete() {
       accRepositories.delete(loggedInAcc.getAccId());
       loggedInAcc=null;
    }
    public void showingUserAccounts(String nationalCode){
       List<Account> accounts=accRepositories.readAll();
       if(accounts!=null) {
           for (Account account : accounts
           ) {
               if (Objects.equals(account.getUserNationalCode(), nationalCode)) {
                   System.out.println("acc id: " + account.getAccId() + " amount: " + account.getAmount() + " branch: " + account.getBranchName() + " owner: " + account.getUserNationalCode());
               }

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
                 System.out.println("sender Id: "+transfer.getSenderCardId()+" receiver Id :"+transfer.getReceiverCardId()+" amount: "+transfer.getAmount()+ " In:"+transfer.getDate());
             }
         }
         for (Transaction transaction :transactions
         ) {
             if(transaction.getDate()==date1){
                 System.out.println("Acc Id: "+ transaction.getAccId()+" amount: "+ transaction.getAmount()+ " In:"+ transaction.getDate()+" operation type:"+transaction.getTransactionType());
             }
         }
     }
     public void showingTransactionAndTransfersSinceNow(Date date) throws SQLException{

         java.sql.Date date1=new  java.sql.Date(date.getTime());
         Date date2=new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         Calendar cal1 = Calendar.getInstance();
         cal1.setTime(date2);
         List<Transfer> transfers= transferRepositories.readAll();
         List<Transaction> transactions= transactionRepositories.readAll();
         if(transfers!=null){


         for (Transfer transfer:transfers
         ) {


             if(cal1.compareTo(cal)>0 ){
                 System.out.println("sender Id: "+transfer.getSenderCardId()+" receiver Id :"+transfer.getSenderCardId()+" amount: "+transfer.getAmount()+ " In:"+transfer.getDate());
             }
         }}
         if(transactions!=null){
         for (Transaction transaction :transactions
         ) {


             if(cal1.compareTo(cal)>0){
                 System.out.println("Acc Id: "+ transaction.getAccId()+" amount: "+ transaction.getAmount()+ " In:"+ transaction.getDate()+" operation type:"+transaction.getTransactionType());
             }
         }}
     }
     public void logout(){
        this.loggedInAcc=null;
     }
}
