import CustomExceptions.*;
import Entities.Account;
import Entities.User;
import Services.AccServices;
import Services.AdminService;
import Services.CreditCardServices;
import Services.UserServices;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main<accServices> {
    static Scanner input=new Scanner(System.in);
     static AccServices accServices= new AccServices();;
    static UserServices userServices =new UserServices();
    static AdminService adminService= new AdminService();
    static CreditCardServices creditCardServices= new CreditCardServices();
    public static void main(String[] args) throws SQLException, ParseException, ClassNotFoundException {
        boolean condition=true;
        while(condition){
            System.out.println("0-admin menu"+"\n"+"1-users menu"+ "\n" +"2-exit");
            try {
                int operator=input.nextInt();
                if(operator>2 || operator<0){
                    throw new OutOfRangeException("please enter something in range!");
                }
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
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRangeException e){
                System.out.println(e.getMessage());
            }

        }
    }
    public static void adminMenu(){
          Boolean condition=true;
        String branchName;
        String nationalCode;
          while(condition){
              System.out.println("0-adding new bank "+"\n"+"1-adding new manager "+"\n"+"2-adding new normal employee"+"\n"+" 3-exit");
           try {
               int operator = input.nextInt();
if(operator>3 || operator<0){
    throw new OutOfRangeException("please enter something in range!");
}
               switch (operator) {
                   case 0:
                       System.out.println("please enter  name of the branch you want to build:");
                       branchName = input.next();
                       adminService.createBranch(branchName);
                       break;
                   case 1:
                       try {
                           System.out.println("please enter national code of the manager");
                           nationalCode = input.next();
                           if(nationalCode.length()!=10){
                               throw new NationalCodeException("invalid national code!");
                           }
                           System.out.println("please enter branch of the manager");
                           branchName = input.next();
                           adminService.createManager(nationalCode, branchName);
                       }catch (NationalCodeException e){
                           System.out.println(e.getMessage());
                       }
                     finally {
                           break;
                       }
                   case 2:
                       try{
                           System.out.println("please enter national code of the employee");
                           nationalCode = input.next();
                           if(nationalCode.length()!=10){
                               throw new NationalCodeException("invalid national code!");
                           }
                           System.out.println("please enter name of the branch");
                           branchName = input.next();
                           adminService.createNormalEmployee(nationalCode, branchName);
                       }catch (NationalCodeException e){
                           System.out.println(e.getMessage());
                       }finally {
                           break;
                       }
                   case 3:
                       condition = false;
                       break;
               }
           }catch (InputMismatchException e){
               System.out.println("please enter a number!");
               input.nextLine();
           }
           catch (OutOfRangeException e){
               System.out.println(e.getMessage());
           }
          }
    }
    public static void usersMenu() throws SQLException, ParseException, ClassNotFoundException {
          boolean condition=true;
          while(condition){
              try {
                  System.out.println("0-login " + "\n" + "1-registering a new user" + "\n" + "2-exit");
                  int operator = input.nextInt();
                  if (operator > 2 || operator < 0) {
                      throw new OutOfRangeException("please enter something in range!");
                  }
                  switch (operator) {
                      case 0:
                          userLoginMenu();
                          break;
                      case 1:
                          registerUserMenu();
                          break;
                      case 2:
                          condition = false;
                          break;
                  }
              }
              catch (OutOfRangeException e){
                  System.out.println(e.getMessage());
              }
              catch (InputMismatchException e){
                  System.out.println("please enter a number!");
                  input.nextLine();
              }
          }
    }
    public static void registerUserMenu() throws SQLException, ClassNotFoundException {
        UserServices userServices=new UserServices();
        try {
            System.out.println("enter your national code");
            String nationalCode = input.next();
            long a=Long.valueOf(nationalCode);
            if (nationalCode.length() != 10) {
                throw new NationalCodeException("invalid national code!");
            }

            System.out.println("enter your password");
            String password = input.next();
            System.out.println("enter your acc password");
            String accPassword = input.next();
            System.out.println("enter your branchName");
            String branchName = input.next();
            userServices.create(nationalCode, password, accPassword, branchName);
        }
        catch (NationalCodeException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("please enter a number!");
        }

    }
    public static void userLoginMenu() throws SQLException, ParseException {
        try {
            System.out.println("enter your national code");
            String nationalCode = input.next();
            Long a=Long.valueOf(nationalCode);
            if(nationalCode.length()!=10){
                throw new NationalCodeException("invalid national code!");
            }
            System.out.println("enter your password");
            String password = input.next();
            if (userServices.login(nationalCode, password)) {
                boolean condition = true;

                    while (condition) {
                        try {
                            System.out.println("0-creating a new acc" + "\n" + "1-showing your Accs " + "\n" + "2-login to your acc" + "\n" + "3-exit ");
                            Integer operator = input.nextInt();
                            if (operator > 3 || operator < 0) {
                                throw new OutOfRangeException("please enter something in range!");
                            }
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
                                    condition = false;
                                    break;
                            }

                        } catch (OutOfRangeException e) {
                            System.out.println(e.getMessage());
                        }catch (InputMismatchException e){
                            System.out.println("please enter a number!");
                            input.nextLine();
                        }
                    }

            }
        }catch (NationalCodeException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("please enter a number!");
        }
    }
    public static void accLoginMenu() throws SQLException, ParseException {
        try {
            System.out.println("please enter your acc Id");
            String accId=input.next();
            Long a=Long.valueOf(accId);
            if(accId.length()!=10){
                throw new AccIdException("invalid acc id!");
            }
            System.out.println("please enter your password");
            String accPassword=input.next();
            try {
                Account account = accServices.login(accId, accPassword);
                    boolean condition = true;
                    while (condition) {
                        try {
                            System.out.println("0-adding creditCard" + "\n" + "1-deleting your current credit cart " + "\n" + "2-editing you acc password " +
                                    "\n" + "3-editing your card password" + "\n" + "4-transaction or card to card" + "\n" + "5-showing your transactions till now" + "\n" + "6-exit");
                            int operator = input.nextInt();
                            if (operator > 6 || operator < 0) {
                                throw new OutOfRangeException("please enter something in range!");
                            }
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
                                    String cardId = input.next();
                                    a=Long.valueOf(accId);
                                    if(cardId.length()!=10){
                                        throw new AccIdException("invalid card Id!");
                                    }
                                    creditCardServices.initialize(cardId);

                                    break;

                                case 4:
                                    transactionMenu(account);
                                    break;
                                case 5:
                                    System.out.println("please enter the date (dd/MM/yyyy)");
                                    String date = input.next();
                                    String dates[]=date.split("/");
                                    if(date.length()<10 || date.length()>10 || Integer.valueOf(dates[0])>30 ||Integer.valueOf(dates[1])>12 || Integer.valueOf(dates[2])>2022 ){
                                       throw new DateFormatException("invalid date type!");
                                    }
                                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                                    System.out.println("please enter the acc id");
                                    String accId1 = input.next();
                                    a=Long.valueOf(accId1);
                                    if(accId1.length()!=10){
                                        throw new AccIdException("invalid acc id!");
                                    }
                                    accServices.showingTransactionAndTransfersSinceNow(date1, accId1);
                                    break;
                                case 6:
                                    condition = false;
                                    accServices.logout();
                                    break;
                            }
                        }catch (OutOfRangeException e){
                            System.out.println(e.getMessage());
                        }catch (AccIdException e){
                            System.out.println(e.getMessage());
                        }catch (DateFormatException e){
                            System.out.println(e.getMessage());
                        }catch (NumberFormatException e){
                            System.out.println("please enter a number!");
                        }catch (InputMismatchException e){
                            System.out.println("please enter a number!");
                            input.nextLine();
                        }
                    }
                }catch (NullPointerException e){
                System.out.println("there is no acc with this details!");
                }
        }catch (AccIdException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }

    }
    public static void transactionMenu(Account account) throws SQLException, ParseException {
        boolean condition=true;
        while(condition) {
            try {
                System.out.println("0-withdrew" + "\n" + "1-deposit" + "\n" + "2_card to card" + "\n" + "3-exit");
                int operator = input.nextInt();
                if(operator>3 || operator<0){
                    throw new OutOfRangeException("please enter something in range!");
                }
                try {
                    switch (operator) {
                        case 0:
                            System.out.println("please enter the amount");
                            int amount = input.nextInt();
                            creditCardServices.transaction(0, amount, account);
                            break;
                        case 1:
                            System.out.println("please enter the amount");
                            int amount1 = input.nextInt();
                            creditCardServices.transaction(1, amount1, account);
                            break;
                        case 2:
                            System.out.println("please enter your card id ");
                            String senderCardId = input.next();
                            Long a=Long.valueOf(senderCardId);
                            if(senderCardId.length()!=10){
                                throw new CardIdException("invalid acc id!");
                            }
                            System.out.println("please enter your cvv2");
                            String cvv2 = input.next();
                            a=Long.valueOf(cvv2);
                            if(cvv2.length()!=4){
                                throw new Cvv2Exception("invalid cvv2 !");
                            }
                            System.out.println("please enter your password");
                            String password = input.next();
                            System.out.println("please enter the expire date of your card(dd/mm/yyyy)");
                            String expireDate = input.next();
                            String[] expireDates=expireDate.split("/");
                            if(expireDate.length()<10 || expireDate.length()>10 || Integer.valueOf(expireDates[0])>30 ||Integer.valueOf(expireDates[1])>12 || Integer.valueOf(expireDates[2])>2028 ){
                                throw new DateFormatException("invalid date type!");
                            }
                            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(expireDate);
                            System.out.println("please enter the amount");
                            int amount2 = input.nextInt();
                            System.out.println("please enter the card id you want to send money");
                            String receiverCardId = input.next();
                            a=Long.valueOf(receiverCardId);
                            if(receiverCardId.length()!=10){
                                throw new CardIdException("invalid card id!");
                            }
                            creditCardServices.transfer(senderCardId, receiverCardId, cvv2, password, date1, amount2);
                            break;
                        case 3:
                            condition = false;
                            break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("please enter a valid amount!");
                    input.nextLine();
                }catch (CardIdException e){
                    System.out.println(e.getMessage());
                }catch (Cvv2Exception e){
                    System.out.println(e.getMessage());
                }catch (DateFormatException e){
                    System.out.println(e.getMessage());
                }catch (NumberFormatException e){
                    System.out.println("please enter a number!");
                    input.nextLine();
                }
            }catch (OutOfRangeException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }
        }
    }
}
