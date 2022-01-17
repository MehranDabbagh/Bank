package Entities;

import java.util.Random;

public class Account {
    private String accId;
    private Status status;
    private String password;
    private Integer amount;
    private String branchName;
    private CreditCard creditCard;
    private Integer foul;
    private User user;
    public Account(String password,String branchName){
        this.accId = String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15))) ;
        this.status = Status.OPEN;
        this.password = password;
        this.amount = 10000;
        this.branchName=branchName;
        this.foul=0;
    }
    public Account(String password, String branchName,User user) {
        this.accId = String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15))) ;
        this.status = Status.OPEN;
        this.password = password;
        this.amount = 10000;
        this.branchName=branchName;
        this.foul=0;
        this.user=user;
    }
    public Account(String password,String accId, String branchName,int amount,Status status,User user) {
        this.accId = accId;
        this.status =status;
        this.password = password;
        this.amount = amount;
        this.branchName=branchName;

        this.foul=0;
        this.user=user;
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getAccId() {
        return accId;
    }
    public void setAccId(String accId) {
        this.accId = accId;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public CreditCard getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    public Integer getFoul() {
        return foul;
    }
    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
