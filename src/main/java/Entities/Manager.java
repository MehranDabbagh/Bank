package Entities;

public class Manager extends Employee {

    public Manager(String nationalCode, JobType jobType, String branchName) {
        super.nationalCode = nationalCode;
        super.jobType = JobType.MANAGER;
        super.branchName=branchName;
    }
    public Manager() {
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
    public JobType getJobType() {
        return jobType;
    }
    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}

