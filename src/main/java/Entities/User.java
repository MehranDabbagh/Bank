package Entities;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String nationalCode;
    private String password;
    private ArrayList<Account> accounts=new ArrayList<Account>();
    public User(String nationalCode){
        this.nationalCode=nationalCode;
    }
    public User(String nationalCode,String password){
        this.nationalCode=nationalCode;
        this.password=password;
    }
    public User(String nationalCode,String password,Account account) {
        this.nationalCode = nationalCode;
        this.password=password;
        accounts.add(account);
    }
    public User() {
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
