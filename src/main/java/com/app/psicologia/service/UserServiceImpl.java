package com.app.psicologia.service;

import com.app.psicologia.model.document.User;
import com.app.psicologia.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        return  userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Integer id) throws Exception {
        if(userRepository.findById(id)!= null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user= userRepository.findById(id);
        return user;
    }

   /* @Override
    public User findByUsername(String username) throws Exception {
        User user= userRepository.findByUsername(username);
        return user;
    }*/

    @Override
    public User findByEmail(String email) throws Exception {
        User user= userRepository.findByEmail(email);
        return user;
    }
}
