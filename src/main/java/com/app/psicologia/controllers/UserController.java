package com.app.psicologia.controllers;


import com.app.psicologia.config.JwtTokenUtils;
import com.app.psicologia.model.JwtRequest;
import com.app.psicologia.model.User;
import com.app.psicologia.service.JwtUserDetailsService;
import com.app.psicologia.service.UserService;
import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

@RequestMapping(value = "/api", produces = { "application/json" })
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JSONObject accessToken= new JSONObject();
        accessToken.put("token",token);
        return ResponseEntity.ok(accessToken);
    }
    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

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
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "application/json" })

    public @ResponseBody User searchUser(@RequestBody User user) throws Exception {

        user=userService.findByUsername(user.getUserName());
        return user;
    }
}
