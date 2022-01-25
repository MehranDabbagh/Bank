package Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreditCard {
    private String cardId;
    private String cvv2;
    private String password;
    private Date expireDate;
    private Status status;
  private String accId;
    private Integer foul;
    public CreditCard(String password,String accId) {
        Random random=new Random();
        this.cardId="";
        for(int i=0;i<10;i++){
            this.cardId=this.cardId+String.valueOf(random.nextInt(9));
        }

        this.cardId="";
        for(int i=0;i<4;i++){
            this.cardId=this.cardId+String.valueOf(random.nextInt(9));
        }
        this.password = password;
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, 5);
        this.expireDate =now.getTime();
        this.status=Status.OPEN;
        this.foul=0;
       this.accId=accId;
    }
    public CreditCard(String cardId,String cvv2,String password,Date expireDate,Status status){
        this.cardId=cardId;
        this.cvv2=cvv2;
        this.password=password;
        this.expireDate=expireDate;
        this.status=status;
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

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }
}
