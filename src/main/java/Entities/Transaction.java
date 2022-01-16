package Entities;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    public Transaction(String transactionId, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
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








