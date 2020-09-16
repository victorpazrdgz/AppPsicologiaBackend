package com.app.psicologia.repository;

import com.app.psicologia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {


    User findById(String id);
    User findByEmail(String email);
    User findByUserName(String username);
    @Query("{'$or':[{'userName' : ?0} , {'email' : ?0}]}")
    User findByEmailOrUserName(String usernameOrEmail);
    void deleteById(String id);

}
