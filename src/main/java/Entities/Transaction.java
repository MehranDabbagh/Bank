package Entities;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    private Integer amount;
    private String accId;
    private Date date;
    public Transaction(String transactionId, TransactionType transactionType, Integer amount,String accId,Date date) {
 this.transactionId=transactionId;
        this.transactionType = transactionType;
        this.amount=amount;
        this.accId=accId;
        this.date=date;
    }
    public Transaction( TransactionType transactionType, Integer amount,String accId,Date date){
        this.transactionId = String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15)));
        this.transactionType = transactionType;
        this.amount=amount;
        this.accId=accId;
        this.date=date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transaction() {
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}








