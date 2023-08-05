package com.nextuple.walletApp.backend.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginUser {
    @NotBlank @NotNull @Email
    private String email;
    @NotBlank @NotNull
    private String password;
}

//Model for login request