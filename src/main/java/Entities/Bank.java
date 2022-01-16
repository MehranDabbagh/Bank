package Entities;

public class Bank {
    private String branchName;
    public Bank(String branchName) {
        this.branchName = branchName;
    }
    public Bank() {
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
