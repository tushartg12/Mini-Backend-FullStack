package com.nextuple.walletApp.backend.entity;

import lombok.Data;

@Data
public class Transaction {
    private long transId;
    private String type;
    private String destination;
    private double amount;
    private String date;
}

//Model for a transaction