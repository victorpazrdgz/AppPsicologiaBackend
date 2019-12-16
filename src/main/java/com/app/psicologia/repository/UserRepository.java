package com.app.psicologia.repository;

import com.app.psicologia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,Long> {


    User findById(String id);
    User findByEmail(String email);
    User findByUserName(String username);
    void deleteById(String id);

}
