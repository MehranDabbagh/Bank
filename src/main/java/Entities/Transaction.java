package Entities;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    private Integer amount;
    private Account account;
    private Date date;
    public Transaction(String transactionId, TransactionType transactionType, Integer amount,Account account,Date date) {
        this.transactionId = String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15)));
        this.transactionType = transactionType;
        this.amount=amount;
        this.account=account;
        this.date=date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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








