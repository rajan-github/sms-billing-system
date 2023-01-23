package com.rajan.smsbillingsystem.models;

public enum PlanType {
    BASIC(0, 0.001),
    SILVER(100, 0.002),
    GOLD(1000, 0.003);

    private int freeMsg;
    private double rate;

    PlanType(int freeMsgCount, double rate) {
        this.freeMsg = freeMsgCount;
        this.rate = rate;
    }

    public int getFreeMsg() {
        return freeMsg;
    }

    public double getRate() {
        return rate;
    }
}
