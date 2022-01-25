package Entities;

import java.util.Random;

public class Account {
    private String accId;
    private Status status;
    private String password;
    private Integer amount;
    private String branchName;
    private Integer foul;
    private String userNationalCode;

    public Account(String password,String userNationalCode,String branchName){
        Random random=new Random();
        this.accId="";
        for(int i=0;i<10;i++){
            this.accId=this.accId+String.valueOf(random.nextInt(9));
        }
        this.status = Status.OPEN;
        this.password = password;
        this.amount = 10000;
        this.userNationalCode=userNationalCode;
        this.foul=0;
        this.branchName=branchName;
    }
    public Account(String password,String userNationalCode,String branchName,String accId)
    {
        this.accId = accId;
        this.status = Status.OPEN;
        this.password = password;
        this.amount = 10000;
        this.userNationalCode=userNationalCode;
        this.foul=0;
        this.branchName=branchName;
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

    public Integer getFoul() {
        return foul;
    }
    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public String getUserNationalCode() {
        return userNationalCode;
    }

    public void setUserNationalCode(String userNationalCode) {
        this.userNationalCode = userNationalCode;
    }
}
