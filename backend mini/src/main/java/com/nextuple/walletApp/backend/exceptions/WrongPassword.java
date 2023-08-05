package com.nextuple.walletApp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "Incorrect Password")
public class WrongPassword extends RuntimeException{
}

//Wrong password exception