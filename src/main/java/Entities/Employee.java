package Entities;

 public class Employee {
     private String nationalCode;
     private JobType jobType;
     private String branchName;
     public Employee(String nationalCode, JobType jobType, String branchName) {
         this.nationalCode = nationalCode;
         this.jobType = jobType;
         this.branchName=branchName;
     }
     public Employee() {
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
