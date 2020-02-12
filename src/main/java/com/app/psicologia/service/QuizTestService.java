package com.app.psicologia.service;

import com.app.psicologia.model.QuizTest;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface QuizTestService {
    public Boolean createTest(QuizTest test) throws Exception ;
    public QuizTest updateTest(QuizTest test) throws Exception ;
    public Boolean deleteTest(Long id) throws Exception ;
    public List<QuizTest> findTests();
    QuizTest findById(String id) throws Exception ;
    QuizTest findByTestName(String testName) throws Exception ;
    public Long getNextSequence(String seqNumber);
}
