package Entities;

import java.util.Date;

public class Transfer {
    private String transferId;
    private CreditCard senderCard;
    private CreditCard ReceiverCard;
    private Integer amount;
    private Date date;

    public Transfer( CreditCard senderCard, CreditCard ReceiverCard, Integer amount,Date date) {
        this.transferId =String.valueOf(Math.floor(Math.random()*(1000)+Math.pow(10,15))) ;
        this.senderCard = senderCard;
        this.ReceiverCard = ReceiverCard;
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

    public CreditCard getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(CreditCard senderCard) {
        this.senderCard = senderCard;
    }

    public CreditCard getReceiverCard() {
        return ReceiverCard;
    }

    public void setReceiverCard(CreditCard receiverCard) {
        ReceiverCard = receiverCard;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
