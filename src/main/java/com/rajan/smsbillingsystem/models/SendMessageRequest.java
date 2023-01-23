package com.rajan.smsbillingsystem.models;

import java.util.List;

public class SendMessageRequest {
    private String messageContent;
    private Long customerId;
    private List<String> messageRecipients;

    public SendMessageRequest() {
    }

    public SendMessageRequest(String messageContent, Long customerId, List<String> messageRecipients) {
        this.messageContent = messageContent;
        this.customerId = customerId;
        this.messageRecipients = messageRecipients;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<String> getMessageRecipients() {
        return messageRecipients;
    }
}
