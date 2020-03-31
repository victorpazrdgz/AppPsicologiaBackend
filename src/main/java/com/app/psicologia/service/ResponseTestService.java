package com.app.psicologia.service;


import com.app.psicologia.model.ResponseTest;
import org.springframework.stereotype.Service;

@Service
public interface ResponseTestService {
    public Boolean saveResponses(ResponseTest responses) throws Exception ;
    public Long getNextSequence(String seqNumber);
}
