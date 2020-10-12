package com.app.psicologia.controllers;


import com.app.psicologia.config.JwtTokenUtils;
import com.app.psicologia.model.User;
import com.app.psicologia.service.EmailService;
import com.app.psicologia.service.JwtUserDetailsService;
import com.app.psicologia.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    EmailService emailService;

    @Value("${pass}")
    public String passReset;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        User user = userService.findByUsername(userDetails.getUsername());
        if (user.getLastLogin()!=null) {
            user.setLastLogin(new Date());
            userService.updateUser(user);
        }
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
        String subject = "Confirm Resgistration";
        if (user != null) {
            User userCreate;
            userCreate= user;
            userCreate.setId(userService.getNextSequence("customSequences"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            userCreate.setPassword(hashedPassword);
            userCreate.setRole("USER");
            userCreate.setLastLogin(new Date());
            user = userService.createUser(userCreate);
            emailService.sendSimpleMessage(user.getEmail(),subject,"Your are Registered");

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
    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE, produces = { "application/json" })
    public @ResponseBody Boolean deleteUser(@RequestBody User user) throws Exception {
        System.out.println("id"+user.getId());
        return userService.deleteUser(user.getId());
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
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    Boolean resetPassword(@RequestParam String userName) throws Exception{
        String subject = "Change Password";
        User user = userService.findByUsername(userName);
        if (user !=null) {
            user.setLastLogin(null);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(passReset);
            user.setPassword(hashedPassword);
            userService.updateUser(user);
            emailService.sendSimpleMessage(user.getEmail(),subject,"Your new Password is :" + passReset);
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    User changePassword(@RequestParam String username, @RequestParam String oldpassword, @RequestParam String password) throws Exception {
        System.out.println("sector"+username);
        System.out.println("sector"+oldpassword);
        User user=userService.findByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//       String hashedPassword = passwordEncoder.hashCode(oldpassword);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.matches(oldpassword,user.getPassword()));
        if(passwordEncoder.matches(oldpassword,user.getPassword())== true) {
            System.out.println(user.getPassword());
            String hashedPassword= passwordEncoder.encode(password);
            user.setLastLogin(new Date());
            user.setPassword(hashedPassword);
            userService.updateUser(user);
        }
        return user;
    }

}
