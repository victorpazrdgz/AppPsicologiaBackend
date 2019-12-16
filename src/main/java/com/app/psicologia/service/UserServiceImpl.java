package com.app.psicologia.service;

import com.app.psicologia.model.CustomSequences;
import com.app.psicologia.model.User;
import com.app.psicologia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongo;

    @Override
    public User createUser(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        System.out.println("update service");
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Long id) throws Exception {
        if (userRepository.findById(id) != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User findById(String id) throws Exception {
        User user = userRepository.findById(id);
        return user;
    }
    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }
    @Override
    public User findByUsername(String username) throws Exception {
        User user=userRepository.findByUserName(username);
        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public Long getNextSequence(String seqNumber) {
        CustomSequences counter = mongo.findAndModify(
                query(where("_id").is(seqNumber)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);
        return counter.getSeq();
    }

}
