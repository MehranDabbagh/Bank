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
            System.out.println("0-admin menu"+"\n"+"1-users menu"+ "\n" +"2-exit");
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
              System.out.println("0-adding new bank "+"\n"+"1-adding new manager "+"\n"+"2-adding new normal employee"+"\n"+" 3-exit");
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
              System.out.println("0-login "+"\n"+"1-registering a new user"+"\n"+"2-exit");
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
                System.out.println("0-creating a new acc" +"\n"+"1-showing your Accs "+"\n"+"2-login to your acc"+"\n"+"3-exit ");
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

                System.out.println("0-adding creditCard" +"\n"+"1-deleting your current credit cart "+"\n"+"2-editing you acc password " +
                        "\n"+"3-editing your card password" +"\n"+"4-transaction or card to card"+"\n"+"5-showing your transactions till now"+"\n"+"6-exit");
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
                        System.out.println("please enter your card Id");
                        String cardId=input.next();
                        creditCardServices.initialize(cardId);

                        break;

                case 4:
                    transactionMenu(account);
                    break;
                case 5:
                    System.out.println("please enter the date (dd/MM/yyyy)");
                    String date = input.next();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    System.out.println("please enter the acc id");
                    String accId1=input.next();
                    accServices.showingTransactionAndTransfersSinceNow(date1,accId1);
                    break;
                case 6:
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
            System.out.println("0-withdrew"+ "\n"+ "1-deposit"+"\n"+ "2_card to card"+"\n"+"3-exit");
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
