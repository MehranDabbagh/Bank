package Entities;

public class Bank {
    private String branchName;
    private String managerName;
    public Bank(String branchName,String managerName) {
        this.branchName = branchName;
        this.managerName=managerName;
    }
    public Bank() {
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
