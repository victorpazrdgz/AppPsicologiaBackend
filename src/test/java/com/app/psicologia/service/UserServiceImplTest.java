package com.app.psicologia.service;

import com.app.psicologia.model.User;

import com.app.psicologia.repository.UserRepository;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public  class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User test;
    private List<String> list;
    @BeforeEach
    public void setUp() {
        test = new User();
        test.setUserName("testUsername");
        test.setName("name");
        when(userRepository.findByUserName("testUsername")).thenReturn(test);
    }
    @Test
    void findByUsername() throws Exception {
        String userName = "testUsername";
        String name = "name";
        User found = userService.findByUsername(userName);
        assertEquals (found.getUserName(),userName);
        assertEquals (found.getName(),name);
    }
}