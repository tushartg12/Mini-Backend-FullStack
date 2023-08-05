package com.nextuple.walletApp.backend.entity;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MoneyTransfer {
    @NotNull @NotBlank @Email
    private String sender;
    @NotNull @NotBlank @Email
    private String receiver;
    @Positive
    private double amount;
}

//Model for money transfer request