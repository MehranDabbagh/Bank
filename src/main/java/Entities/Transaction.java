package Entities;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    private Integer amount;
    private String accId;
    public Transaction(String transactionId, TransactionType transactionType,Integer amount,String accId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount=amount;
        this.accId=accId;
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
}








