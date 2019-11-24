package com.app.psicologia.service;


import com.app.psicologia.model.document.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User createUser(User user) throws Exception ;
    public User updateUser(User user) throws Exception ;
    public Boolean deleteUser(Integer id) throws Exception ;
    User findById(Integer id) throws Exception ;
   /* User findByUsername(String username) throws Exception ;*/
    User findByEmail(String email) throws Exception ;
}
