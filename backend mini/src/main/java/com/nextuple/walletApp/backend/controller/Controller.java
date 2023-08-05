package com.nextuple.walletApp.backend.controller;

import com.nextuple.walletApp.backend.entity.LoginUser;
import com.nextuple.walletApp.backend.entity.MoneyTransfer;
import com.nextuple.walletApp.backend.entity.User;
import com.nextuple.walletApp.backend.entity.WalletRecharge;
import com.nextuple.walletApp.backend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/signup")  //To add a User
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        //.CREATED means 201 response code
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")  //To log in the user
    public ResponseEntity<LoginUser> findUser(@RequestBody @Valid LoginUser loginUser){
        return new ResponseEntity<>(service.findUser(loginUser), HttpStatus.OK);
    }
    @PostMapping("/wallet-recharge")  //For recharging the wallet
    public ResponseEntity<User> walletRecharge(@RequestBody @Valid WalletRecharge walletRecharge){
        return new ResponseEntity<>(service.walletRecharge(walletRecharge), HttpStatus.OK);
    }
    @PostMapping("/money-transfer")  //For money transfer
    public ResponseEntity<User> moneyTransfer(@RequestBody @Valid MoneyTransfer moneyTransfer){
        return new ResponseEntity<>(service.moneyTransaction(moneyTransfer), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")  //to find a user by its id
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }
}
