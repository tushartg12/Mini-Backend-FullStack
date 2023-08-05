package com.nextuple.walletApp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED,reason = "Insufficient Funds")
public class InsufficientBalance extends RuntimeException{
}

//Insufficient balance exception