import Entities.Account;
import Entities.User;
import Services.AccServices;
import Services.AdminService;
import Services.CreditCardServices;
import Services.UserServices;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
     static AccServices accServices=new AccServices();
   static UserServices userServices=new UserServices();
   static AdminService adminService=new AdminService();
   static CreditCardServices creditCardServices=new CreditCardServices();
    public static void main(String[] args) throws SQLException, ParseException {
        boolean condition=true;
        while(condition){
            System.out.println("enter 0 for admin menu and 1 for users menu and 2 for exit");
            int operator=input.nextInt();
            switch (operator){
                case 0:
                    System.out.println("please enter your username and password");
                    String username=input.next();
                    String password=input.next();
                    if(Objects.equals(username, "admin") && Objects.equals(password, "admin")){
                     adminMenu();
                    }
                    break;
                case 1: usersMenu();break;
                case 2:condition=false;break;
            }
        }
    }
    public static void adminMenu(){
          Boolean condition=true;
        String branchName;
        String nationalCode;
          while(condition){
              System.out.println("enter 0 for adding bank 1 for adding manager 2 for adding normal employee and 3 for exit");
              int operator=input.nextInt();
              switch (operator){
                  case 0:
                      System.out.println("please enter  name of the branch you want to build:");
                       branchName=input.next();
                      adminService.createBranch(branchName);
                      break;
                  case 1:
                      System.out.println("please enter national code of the manager");
                       nationalCode=input.next();
                      System.out.println("please enter national code of the manager");
                       branchName=input.next();
                      adminService.createManageer(nationalCode,branchName);
                      break;
                  case 2:
                      System.out.println("please enter national code of the manager");
                       nationalCode=input.next();
                      System.out.println("please enter national code of the manager");
                      branchName=input.next();
                      adminService.createNormalEmployee(nationalCode,branchName);
                      break;
                  case 3:condition=false;break;
              }
          }
    }
    public static void usersMenu() throws SQLException, ParseException {
          boolean condition=true;
          while(condition){
              System.out.println("enter 0 for login and 1 for registering a new user and 2 for exit");
              int operator=input.nextInt();
              switch (operator){
                  case 0:userLoginMenu();break;
                  case 1:registerUserMenu();break;
                  case 2:condition=false;break;
              }

          }
    }
    public static void registerUserMenu(){
        UserServices userServices=new UserServices();
        System.out.println("enter your national code");
        String nationalCode=input.next();
        System.out.println("enter your password");
        String password=input.next();
        System.out.println("enter your acc password");
        String accPassword=input.next();
        userServices.create(nationalCode,password);
    }
    public static void userLoginMenu() throws SQLException, ParseException {

        System.out.println("enter your national code");
        String nationalCode=input.next();
        System.out.println("enter your password");
        String password=input.next();
        if(userServices.login(nationalCode,password)){

            System.out.println("enter 0 for creating a new acc and 1 for showing your Accs and 2 for login to your acc and 3 for exit ");
            Integer operator=input.nextInt();
            User user=new User(nationalCode,password);
            switch (operator){
                case 0:accServices.create(user);break;
                case 1:accServices.showingUserAccounts();break;
                case 2:accLoginMenu();break;
                case 3:accServices.logout();break;
            }
        }
    }
    public static void accLoginMenu() throws SQLException, ParseException {
        System.out.println("please enter your acc Id");
        String accId=input.next();
        System.out.println("please enter your password");
        String accPassword=input.next();
        Account account=accServices.login(accId,accPassword);
        if(account!=null){
            boolean conditon=true;
            while(true) {
                System.out.println("welcome " + account.getUser().getNationalCode() + "!");
                System.out.println("enter 0 for adding creditCard 1 for deleting your current credit cart and 2 for editing you acc password " +
                        "and 3 for editing your card password and 4 for transaction or card to card and 5 for showing your transactions till now and 6 for transaction in specific date and 7 exit ");
                int operator = input.nextInt();
                switch (operator) {
                    case 0:
                        creditCardServices.create(account);
                        break;
                    case 1:
                        creditCardServices.delete();
                        break;
                    case 2:
                        System.out.println("enter your new password");
                        String accNewPassword = input.next();
                        accServices.update(accPassword);
                        break;
                    case 3:
                      if(Objects.equals(account.getCreditCard().getPassword(), "?")){
                          System.out.println("please initialize your password ");
                          String cardNewPassword = input.next();
                          creditCardServices.update(cardNewPassword);
                          break;
                      }else {
                          System.out.println("please enter your old password");
                          String cardPassword = input.next();
                          if (creditCardServices.login(account.getCreditCard().getCardId(), cardPassword)) {
                              System.out.println("please enter your new password");
                              String cardNewPassword = input.next();
                              creditCardServices.update(cardNewPassword);break;
                          } else System.out.println("password is wrong!");break;}
                    case 4:transactionMenu();break;
                    case 5:
                        System.out.println("please enter the date (dd/MM/yyyy)");
                        String date=input.next();
                        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        accServices.showingTransactionAndTransfersSinceNow(date1);break;
                    case 6: System.out.println("please enter the date (dd/MM/yyyy)");
                        String date3=input.next();
                        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(date3);
                        accServices.showingTransactionAndTransfers(date2);break;
                    case 7:conditon=false;accServices.logout();break;
                }
            }
        }

    }
    public static void transactionMenu()throws SQLException{
        boolean conditon=true;
        while(true) {
            System.out.println("enter 0 for for withdrew and 1 for deposit and 2 for card to card and 3 for exit");
            int operator = input.nextInt();
            switch (operator) {
                case 0:
                    System.out.println("please enter the amount");
                    int amount = input.nextInt();
                    creditCardServices.transaction(0, amount);
                    break;
                case 1:
                    System.out.println("please enter the amount");
                    int amount1 = input.nextInt();
                    creditCardServices.transaction(1, amount1);
                    break;
                case 2:
                    System.out.println("enter 0 for card to card online and 1 for card to card from ATM");
                    int operator1 = input.nextInt();
                    if (operator1 == 0) {
                        System.out.println("please enter your id");
                        String senderId = input.next();
                        System.out.println("please enter your cvv2");
                        String cvv2 = input.next();
                        System.out.println("please enter your password");
                        String password = input.next();
                        System.out.println("please enter the card id you want to send money");
                        String receiverId = input.next();
                        System.out.println("please enter the amount");
                        int amount2 = input.nextInt();
                        creditCardServices.transferWithoutLogin(senderId, password, cvv2, receiverId, amount2);
                    } else if (operator1 == 1) {
                        System.out.println("please enter the card id you want to send money");
                        String receiverId = input.next();
                        System.out.println("please enter the amount");
                        int amount2 = input.nextInt();
                        creditCardServices.transfer(receiverId, amount2);
                    }
                    break;
                case 3:conditon=false;break;
            }
        }
    }
}
