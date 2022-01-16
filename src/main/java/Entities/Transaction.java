package Entities;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    public Integer amount;
    public Transaction(String transactionId, TransactionType transactionType, Integer amount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount=amount;
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








