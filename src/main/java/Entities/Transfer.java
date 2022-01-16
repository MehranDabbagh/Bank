package Entities;

public class Transfer {
    private String transactionId;
        private String senderId;
        private Integer amount;
        private String reciverId;

    public Transfer(String transactionId, String senderId, Integer amount, String reciverId) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.amount = amount;
        this.reciverId = reciverId;
    }

    public Transfer() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReciverId() {
        return reciverId;
    }

    public void setReciverId(String reciverId) {
        this.reciverId = reciverId;
    }
}
