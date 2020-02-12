package com.app.psicologia.service;


import com.app.psicologia.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User createUser(User user) throws Exception ;
    public User updateUser(User user) throws Exception ;
    public Boolean deleteUser(Long id) throws Exception ;
    public List<User> findUsers();
    User findById(String id) throws Exception ;
    User findByUsername(String username) throws Exception ;
    User findByEmail(String email) throws Exception ;
    public Long getNextSequence(String seqNumber);
}
