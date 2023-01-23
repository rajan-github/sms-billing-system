package com.rajan.smsbillingsystem.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SentMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer sentBy;
    private String sentTo;

    @Temporal(TemporalType.DATE)
    private Date sentDate;

    private String messageContent;

    protected SentMessages() {
    }

    public SentMessages(Customer sentBy, String sentTo, Date sentDate, String messageContent) {
        this.sentBy = sentBy;
        this.sentTo = sentTo;
        this.sentDate = sentDate;
        this.messageContent = messageContent;
    }

    public Customer getSentBy() {
        return sentBy;
    }

    public void setSentBy(Customer sentBy) {
        this.sentBy = sentBy;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
