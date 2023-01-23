package com.rajan.smsbillingsystem.models;

import lombok.Builder;

import java.util.List;

@Builder
public class SendMessageResponse {
    private List<String> messageRecipients;
    private String cost;
    private PlanType planType;

    public SendMessageResponse() {
    }

    public SendMessageResponse(List<String> messageRecipients, String cost, PlanType planType) {
        this.messageRecipients = messageRecipients;
        this.cost = cost;
        this.planType = planType;
    }

    public List<String> getMessageRecipients() {
        return messageRecipients;
    }

    public String getCost() {
        return cost;
    }

    public PlanType getPlanType() {
        return planType;
    }
}
