package com.app.psicologia.controller;


import com.app.psicologia.model.document.User;
import com.app.psicologia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = { "application/json" })
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/new", method = RequestMethod.POST, produces = { "application/json" })
    public @ResponseBody User createUser(@RequestBody User user) throws Exception {
        System.out.println("hola controller");
         System.out.println(user);
        if (user != null) {

            user = userService.createUser(user);

        }
        return user;
    }
}
