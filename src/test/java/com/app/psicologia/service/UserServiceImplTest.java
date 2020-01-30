package com.app.psicologia.service;

import com.app.psicologia.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    User user;
    @Before
    public void setup() throws Exception {
        user=new User();
        user.setName("test");
        user.setUserName("testUser");
    }
    @Test
    void findByUsername() throws Exception {
        Mockito.when(userService.findByUsername(user.getUserName())).thenReturn(user);
        User found = userService.findByUsername("testUser");
        assertEquals(found.getName(),"test");
    }
}