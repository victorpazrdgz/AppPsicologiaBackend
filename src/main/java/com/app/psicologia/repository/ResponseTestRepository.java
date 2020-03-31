package com.app.psicologia.repository;


import com.app.psicologia.model.ResponseTest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseTestRepository extends MongoRepository<ResponseTest, Long> {

    ResponseTest findById(String id);

    void deleteById(String id);
}
