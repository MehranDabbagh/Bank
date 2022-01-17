package Entities;

import java.util.ArrayList;

public class Bank {
    private String branchName;
   private Manager manager;
   private ArrayList<Account> accounts=new ArrayList<Account>();
   private ArrayList<NormalEmployee> normalEmployee=new ArrayList<NormalEmployee>();
    public Bank(String branchName) {
        this.branchName = branchName;

    }
    public Bank(String branchName,Manager manager){
        this.branchName=branchName;
        this.manager=manager;
    }
    public Bank(String branchName,Manager manager,ArrayList<NormalEmployee> normalEmployee){
        this.branchName=branchName;
        this.manager=manager;
        this.normalEmployee=normalEmployee;
    }
    public Bank(String branchName,Manager manager,ArrayList<NormalEmployee> normalEmployee,ArrayList<Account> accounts){
        this.branchName=branchName;
        this.manager=manager;
        this.normalEmployee=normalEmployee;
        this.accounts=accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<NormalEmployee> getNormalEmployee() {
        return normalEmployee;
    }

    public void setNormalEmployee(ArrayList<NormalEmployee> normalEmployee) {
        this.normalEmployee = normalEmployee;
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
