package Entities;

import java.util.Date;

public class Transfer {
    private String transferId;
    private String senderCardId;
    private String ReceiverCardId;
    private Integer amount;
    private Date date;

    public Transfer( String senderCardId, String ReceiverCardId, Integer amount,Date date) {
        this.transferId =String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15))) ;
        this.senderCardId = senderCardId;
        this.ReceiverCardId = ReceiverCardId;
        this.amount = amount;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(String senderCardId) {
        this.senderCardId = senderCardId;
    }

    public String getReceiverCardId() {
        return ReceiverCardId;
    }

    public void setReceiverCardId(String receiverCardId) {
        ReceiverCardId = receiverCardId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
