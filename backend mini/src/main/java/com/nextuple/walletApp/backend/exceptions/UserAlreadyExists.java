package com.nextuple.walletApp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE,reason = "User Already Exist")
public class UserAlreadyExists extends RuntimeException {
}

//User already exists exception