package Entities;

public class User {
    private String nationalCode;

    public User(String nationalCode,String userID) {
        this.nationalCode = nationalCode;
    }
    public User() {
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }


}
