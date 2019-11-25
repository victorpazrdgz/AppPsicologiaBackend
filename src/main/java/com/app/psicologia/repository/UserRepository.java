package com.app.psicologia.repository;

import com.app.psicologia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

   // User findByUsername(String userName);
    User findById(Integer id);
    User findByEmail(String email);
    void deleteById(Integer id);

}
