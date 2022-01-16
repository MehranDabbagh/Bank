package Entities;

import java.util.Date;

public class CreditCard {
    private String cardId;
    private String cvv2;
    private String password;
    private Date expireDate;
    private String accId;
    private Status status;
    public CreditCard(String cardId, String cvv2, String password, Date expireDate, String accId) {
        this.cardId = cardId;
        this.cvv2 = cvv2;
        this.password = password;
        this.expireDate = expireDate;
        this.accId=accId;
        this.status=Status.OPEN;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getAccId() {
        return accId;
    }
    public void setAccId(String accId) {
        this.accId = accId;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
