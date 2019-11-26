package com.app.psicologia.controllers;


import com.app.psicologia.model.User;
import com.app.psicologia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = { "application/json" })
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/new", method = RequestMethod.POST, produces = { "application/json" })
    public @ResponseBody User createUser(@RequestBody User user) throws Exception {
        System.out.println("hola controllers");
         System.out.println(user);
        if (user != null) {
            User userCreate = new  User();
            userCreate= user;
            userCreate.setId(userService.getNextSequence("customSequences"));

            user = userService.createUser(userCreate);

        }
        return user;
    }
    @RequestMapping(value = "/user/update", method = RequestMethod.PUT, produces = { "application/json" })

    public @ResponseBody User updateUser(@RequestBody User user) throws Exception {
        if (user != null) {
            System.out.println("update user");
            userService.updateUser(user);

        }
        return user;
    }
    @RequestMapping(value = "/user/{id}/delete", method = RequestMethod.DELETE, produces = { "application/json" })
    public @ResponseBody Boolean deleteUser(@PathVariable Long id) throws Exception {
        System.out.println(userService.deleteUser(id));
        return userService.deleteUser(id);
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = { "application/json" })

    public @ResponseBody List<User> listUsers() throws Exception {
        List<User> listUsers = userService.findUsers();
        return listUsers;
    }
}
