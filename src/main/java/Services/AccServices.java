package Services;

import Entities.Account;
import Entities.Status;
import Entities.User;
import Repositories.AccRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccServices  {
    private AccRepositories accRepositories=new AccRepositories();
    private Account loggedInAcc;
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
    public void update(Integer operator,String newValue) {

     Account test=  accRepositories.readById(loggedInAcc.getPassword());
     if(operator>=0 && operator<2 && newValue!=null) {
         switch (operator) {
             case 0:
                 test.setPassword(newValue);
                 accRepositories.update(test);
                 break;
             case 1:
                 test.setStatus(Status.valueOf(newValue));
                 accRepositories.update(test);
                 break;
         }
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
}
