package com.app.psicologia.model.repository;

import com.app.psicologia.model.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

   // User findByUsername(String userName);
    User findById(Integer id);
    User findByEmail(String email);
    void deleteById(Integer id);

}
