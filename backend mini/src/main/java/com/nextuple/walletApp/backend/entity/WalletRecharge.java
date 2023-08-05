package com.nextuple.walletApp.backend.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class WalletRecharge{
    @NotBlank @NotNull @Email
    private String email;
    @Positive
    private double amount;
}

//Model for wallet recharge request