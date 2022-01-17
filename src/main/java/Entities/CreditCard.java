package Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CreditCard {
    private String cardId;
    private String cvv2;
    private String password;
    private Date expireDate;
    private Status status;
    private Integer foul;
    public CreditCard(String password) {
        this.cardId = String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15))) ;
        this.cvv2 = String.valueOf(Math.floor(Math.random()*(999)+Math.pow(10,3))) ;
        this.password = password;
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, 5);
        this.expireDate =now.getTime();
        this.status=Status.OPEN;
        this.foul=0;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
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
    public Integer getFoul() {
        return foul;
    }
    public void setFoul(Integer foul) {
        this.foul = foul;
    }
}
