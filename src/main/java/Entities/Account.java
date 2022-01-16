package Entities;

public class Account {
    private String accId;
    private Status status;
    private String password;
    private Integer amount;
    private String branchName;
    private String userId;
    public Account(String accId, String status, String password, Integer amount, String branchName,String userId) {
        this.accId = accId;
        this.status = Status.OPEN;
        this.password = password;
        this.amount = amount;
        this.branchName=branchName;
        this.userId=userId;
    }
    public Account() {
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
}
