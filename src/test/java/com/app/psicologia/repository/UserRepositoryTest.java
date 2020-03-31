package com.app.psicologia.repository;

import com.app.psicologia.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User test;
//    @BeforeEach
//    public void setUp() {
//
//        test = new User();
//        test.setUserName("testUsername");
//        test.setName("name");
//        userRepository.save(test);
//    }
//
//
//    @Test
//    public void findByUsernameTest() {
//        // given
//        User testUser = new User();
//        testUser.setUserName("testUsername");
//
//
//        // when
//        User found = userRepository.findByUserName(testUser.getUserName());
//
//        // then
//       assertEquals(found.getUserName(),testUser.getUserName());
//    }
}