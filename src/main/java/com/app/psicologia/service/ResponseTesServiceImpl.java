package com.app.psicologia.service;

import com.app.psicologia.model.CustomSequences;
import com.app.psicologia.model.ResponseTest;
import com.app.psicologia.repository.QuizTestRepository;
import com.app.psicologia.repository.ResponseTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ResponseTesServiceImpl implements ResponseTestService {
    @Autowired
    private ResponseTestRepository responseTestRepository;

    @Autowired
    private MongoOperations mongo;

    @Override
    public Boolean saveResponses(ResponseTest responses) throws Exception {
        responseTestRepository.save(responses);
        return true;
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

    @Override
    public List<ResponseTest> findAllResponses() throws Exception {
        return responseTestRepository.findAll();
    }

    @Override
    public List<ResponseTest> findByTestName(String testName) throws Exception {
        return responseTestRepository.findByTestName(testName);
    }
}
