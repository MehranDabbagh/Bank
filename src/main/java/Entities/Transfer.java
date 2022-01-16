package Entities;

public class Transfer {
    private String transferId;
    private String senderId;
    private String ReceiverId;
    private Integer amount;

    public Transfer(String transferId, String senderId, String receiverId, Integer amount) {
        this.transferId = transferId;
        this.senderId = senderId;
        ReceiverId = receiverId;
        this.amount = amount;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String receiverId) {
        ReceiverId = receiverId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
