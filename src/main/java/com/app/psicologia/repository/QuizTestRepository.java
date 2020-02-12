package com.app.psicologia.repository;

import com.app.psicologia.model.QuizTest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizTestRepository extends MongoRepository<QuizTest,Long> {

    QuizTest findById(String id);
    QuizTest findByTestName(String testName);
    void deleteById(String id);
}
