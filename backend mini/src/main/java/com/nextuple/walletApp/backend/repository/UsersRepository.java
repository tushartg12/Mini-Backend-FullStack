package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UsersRepository extends MongoRepository<User,String> {
}
//Repository for connecting with Users collection in the DB