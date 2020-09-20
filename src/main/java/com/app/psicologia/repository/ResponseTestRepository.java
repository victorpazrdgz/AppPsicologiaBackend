package com.app.psicologia.repository;


import com.app.psicologia.model.ResponseTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ResponseTestRepository extends MongoRepository<ResponseTest, Long> {

    ResponseTest findById(String id);

    void deleteById(String id);
    @Query("{'testName' : ?0}")
    List<ResponseTest> findByTestName(String testName);
}
