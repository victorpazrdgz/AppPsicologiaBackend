package com.app.psicologia.service;

import com.app.psicologia.model.User;

import com.app.psicologia.repository.UserRepository;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public  class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    public User test;

    @Before
    public void setUp() {
        test = new User();
        test.setUserName("testUsername");
        test.setName("name");
        when(userRepository.findByUserName("testUsername")).thenReturn(test);
    }
    @Test
    void findByUsername() throws Exception {
        String userName = "testUsername";
        User found = userService.findByUsername(userName);
        System.out.println(found);
//        assertEquals (found.getUserName(),userName);
    }
}