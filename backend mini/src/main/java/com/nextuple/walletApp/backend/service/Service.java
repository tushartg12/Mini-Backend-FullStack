package com.nextuple.walletApp.backend.service;
import com.nextuple.walletApp.backend.entity.*;

public interface Service {
    User addUser(User user);  //For adding a new user
    LoginUser findUser(LoginUser loginUser);  //for finding a user on login request
    User walletRecharge(WalletRecharge walletRecharge);  //for recharging the wallet
    User moneyTransaction(MoneyTransfer moneyTransfer);  //for transferring money
    User getUserById(String email);  //get the user by its id
}
