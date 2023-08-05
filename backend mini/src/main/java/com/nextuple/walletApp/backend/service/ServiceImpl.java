package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.*;
import com.nextuple.walletApp.backend.exceptions.InsufficientBalance;
import com.nextuple.walletApp.backend.exceptions.UserAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.exceptions.WrongPassword;
import com.nextuple.walletApp.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User addUser(User user) {
        String userId=user.getEmail();
        //if user already exists throw the UserAlreadyExists error
        if(usersRepository.existsById(userId)){
            throw new UserAlreadyExists();
        }
        else {
            User newUser=usersRepository.insert(user);
            newUser.setPassword(null);
            return newUser;
        }
    }

    @Override
    public LoginUser findUser(LoginUser loginUser) {
        String userId=loginUser.getEmail();
        User user=usersRepository.findById(userId).orElse(null);
        //if user not exists throw the UserNotFound error
        if(user==null){
            throw new UserNotFound();
        }
        else {
            if(user.getPassword().equals(loginUser.getPassword())){
                loginUser.setPassword(null);
                return loginUser;
            }
            //if password not matches throw the WrongPassword error
            else {
                throw new WrongPassword();
            }
        }
    }

    @Override
    public User walletRecharge(WalletRecharge walletRecharge) {
        //Create a transaction object for sender
        Transaction transaction=new Transaction();
        transaction.setTransId(new Random().nextLong());
        transaction.setType("Credit");
        transaction.setDestination("Wallet");
        transaction.setAmount(walletRecharge.getAmount());
        transaction.setDate(Instant.now().toString());

        //Update the wallet amount and transaction history of the sender
        String userId=walletRecharge.getEmail();
        User user=usersRepository.findById(userId).get();
        user.setAmount(user.getAmount()+walletRecharge.getAmount());
        List<Transaction> transactionList=user.getTransactions();
        transactionList.add(transaction);
        user.setTransactions(transactionList);
        usersRepository.save(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public User moneyTransaction(MoneyTransfer moneyTransfer) {

        String senderUserId=moneyTransfer.getSender();
        User sender=usersRepository.findById(senderUserId).get();

        String receiverUserId=moneyTransfer.getReceiver();
        User receiver=usersRepository.findById(receiverUserId).orElse(null);

        //if receiver does not exist
        if(receiver==null){
            throw new UserNotFound();
        }
        //if the funds are sufficient in sender wallet
        if(sender.getAmount()>=moneyTransfer.getAmount()) {
            //Create a transaction object for sender
            Transaction transactionSender=new Transaction();
            transactionSender.setTransId(new Random().nextLong());
            transactionSender.setType("Debit");
            transactionSender.setDestination(moneyTransfer.getReceiver());
            transactionSender.setAmount(moneyTransfer.getAmount());
            transactionSender.setDate(Instant.now().toString());

            //Update the wallet amount and transaction history of the sender
            sender.setAmount(sender.getAmount() - moneyTransfer.getAmount());
            List<Transaction> transactionListSender = sender.getTransactions();
            transactionListSender.add(transactionSender);
            sender.setTransactions(transactionListSender);
            usersRepository.save(sender);
        }
        //if funds are insufficient throw InsufficientBalance error
        else {
            throw new InsufficientBalance();
        }

        //Create a transaction object for receiver
        Transaction transactionReceiver=new Transaction();
        transactionReceiver.setTransId(new Random().nextLong());
        transactionReceiver.setType("Credit");
        transactionReceiver.setDestination(moneyTransfer.getSender());
        transactionReceiver.setAmount(moneyTransfer.getAmount());
        transactionReceiver.setDate(Instant.now().toString());

        //Update the wallet amount and transaction history of the receiver
        receiver.setAmount(receiver.getAmount() + moneyTransfer.getAmount());
        List<Transaction> transactionListReceiver = receiver.getTransactions();
        transactionListReceiver.add(transactionReceiver);
        receiver.setTransactions(transactionListReceiver);
        usersRepository.save(receiver);
        sender.setPassword(null);
        return sender;
    }

    @Override
    public User getUserById(String email) {
        User user=usersRepository.findById(email).orElse(null);
        //if user not found throw UserNotFound error
        if(user==null){
            throw new UserNotFound();
        }
        else {
            user.setPassword(null);
            return user;
        }
    }
}

//Note: we are setting the password to null during returning the user. It increases security.
