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
     static AccServices accServices;

    static {
        try {
            accServices = new AccServices();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static UserServices userServices;

    static {
        try {
            userServices = new UserServices();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static AdminService adminService;

    static {
        try {
            adminService = new AdminService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static CreditCardServices creditCardServices;

    static {
        try {
            creditCardServices = new CreditCardServices();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ParseException, ClassNotFoundException {
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
                      System.out.println("please enter branch of the manager");
                       branchName=input.next();
                      adminService.createManager(nationalCode,branchName);
                      break;
                  case 2:
                      System.out.println("please enter national code of the employee");
                       nationalCode=input.next();
                      System.out.println("please enter name of the branch");
                      branchName=input.next();
                      adminService.createNormalEmployee(nationalCode,branchName);
                      break;
                  case 3:condition=false;break;
              }
          }
    }
    public static void usersMenu() throws SQLException, ParseException, ClassNotFoundException {
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
    public static void registerUserMenu() throws SQLException, ClassNotFoundException {
        UserServices userServices=new UserServices();
        System.out.println("enter your national code");
        String nationalCode=input.next();
        System.out.println("enter your password");
        String password=input.next();
        System.out.println("enter your acc password");
        String accPassword=input.next();
        System.out.println("enter your branchName");
        String branchName=input.next();
        userServices.create(nationalCode,password,accPassword,branchName);


    }
    public static void userLoginMenu() throws SQLException, ParseException {

        System.out.println("enter your national code");
        String nationalCode=input.next();
        System.out.println("enter your password");
        String password=input.next();
        if(userServices.login(nationalCode,password)) {
            boolean condition = true;
            while (condition) {
                System.out.println("enter 0 for creating a new acc and 1 for showing your Accs and 2 for login to your acc and 3 for exit ");
                Integer operator = input.nextInt();
                User user = new User(nationalCode, password);

                switch (operator) {
                    case 0:
                        System.out.println("enter password for your acc");
                        String accPassword = input.next();
                        System.out.println("enter branch of the bank");
                        String branchName = input.next();
                        user.setBranchName(branchName);
                        accServices.create(user, accPassword);
                        break;
                    case 1:
                        accServices.showingUserAccounts(nationalCode);
                        break;
                    case 2:
                        accLoginMenu();
                        break;
                    case 3:
                        accServices.logout();
                        condition=false;
                        break;
                }
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
            boolean condition=true;
            while(condition) {

                System.out.println("enter 0 for adding creditCard 1 for deleting your current credit cart and 2 for editing you acc password " +
                        "and 3 for editing your card password and 4 for transaction or card to card and 5 for showing your transactions till now and 6 for transaction in specific date and 7 exit ");
                int operator = input.nextInt();
                switch (operator) {
                    case 0:
                        creditCardServices.create(account);
                        break;
                    case 1:
                        creditCardServices.delete(account);
                        break;
                    case 2:
                        System.out.println("enter your new password");
                        String accNewPassword = input.next();
                        accServices.update(accNewPassword);
                        break;
                    case 3:
                        System.out.println("please enter your cardId");
                        String cardId = input.next();
                        System.out.println("please enter your password (if you didn't initialize enter ? ) ");
                        String cardPassword1 = input.next();
                        if (creditCardServices.login(cardId, cardPassword1)) {
                            System.out.println("please enter your new password");
                            String cardNewPassword= input.next();
                            creditCardServices.update(cardNewPassword);
                        } else System.out.println("password is wrong!");
                        break;

                case 4:
                    transactionMenu(account);
                    break;
                case 5:
                    System.out.println("please enter the date (dd/MM/yyyy)");
                    String date = input.next();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    accServices.showingTransactionAndTransfersSinceNow(date1);
                    break;
                case 6:
                    System.out.println("please enter the date (dd/MM/yyyy)");
                    String date3 = input.next();
                    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date3);
                    accServices.showingTransactionAndTransfers(date2);
                    break;
                case 7:
                    condition = false;
                    accServices.logout();
                    break;
                }
            }
        }
    }



    public static void transactionMenu(Account account) throws SQLException, ParseException {

        boolean condition=true;
        while(condition) {
            System.out.println("enter 0 for for withdrew and 1 for deposit and 2 for card to card and 3 for exit");
            int operator = input.nextInt();
            switch (operator) {
                case 0:
                    System.out.println("please enter the amount");
                    int amount = input.nextInt();
                    creditCardServices.transaction(0, amount,account);
                    break;
                case 1:
                    System.out.println("please enter the amount");
                    int amount1 = input.nextInt();
                    creditCardServices.transaction(1, amount1,account);
                    break;
                case 2:
                    System.out.println("please enter your card id ");
                    String senderCardId=input.next();
                    System.out.println("please enter your cvv2");
                    String cvv2=input.next();
                    System.out.println("please enter your password");
                    String password=input.next();

                    System.out.println("please enter the expire date of your card(dd/mm/yyyy)");
                    String expireDate=input.next();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(expireDate);

                        System.out.println("please enter the amount");
                        int amount2 = input.nextInt();
                    System.out.println("please enter the card id you want to send money");
                    String receiverCardId = input.next();
                        creditCardServices.transfer(senderCardId,receiverCardId, cvv2,password,date1,amount2);
                    break;
                case 3:condition=false;break;
            }
        }
    }
}
