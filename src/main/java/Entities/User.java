package Entities;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String nationalCode;
    private String password;
   private String branchName;


    public User(String nationalCode,String password,String branchName){
        this.nationalCode=nationalCode;
        this.password=password;
        this.branchName=branchName;
    }
    public User(String nationalCode,String password) {
        this.nationalCode = nationalCode;
        this.password=password;

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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
