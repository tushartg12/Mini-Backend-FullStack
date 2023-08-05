package com.nextuple.walletApp.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    @Email
    @NotBlank
    private String email;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull
    private String password;
    @PositiveOrZero
    @NegativeOrZero    //If user try to set amount manually, no amount is acceptable other than zero
    private double amount;
    private List<Transaction> transactions= new ArrayList<>(); //Validation remaining
}

//Model for user details