package com.app.psicologia.service;


import com.app.psicologia.model.ResponseTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResponseTestService {
    public Boolean saveResponses(ResponseTest responses) throws Exception ;
    public Long getNextSequence(String seqNumber) throws Exception;
    public List<ResponseTest> findAllResponses() throws Exception;
    public List<ResponseTest> findByTestName(String testName) throws Exception;
}
