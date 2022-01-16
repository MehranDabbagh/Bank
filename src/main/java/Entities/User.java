package Entities;

public class User {
    private String nationalCode;
    private String userID;
    public User(String nationalCode,String userID) {
        this.nationalCode = nationalCode;
       this.userID=userID;
    }
    public User() {
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

}
