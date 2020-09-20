package com.app.psicologia.service;

import com.app.psicologia.model.CustomSequences;
import com.app.psicologia.model.QuizTest;
import com.app.psicologia.repository.QuizTestRepository;
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
public class QuizTestServiceImpl implements QuizTestService {

    @Autowired
    private QuizTestRepository quizTestRepository;

    @Autowired
    private MongoOperations mongo;

    @Override
    public Boolean createTest(QuizTest test) throws Exception {
        quizTestRepository.save(test);
        return true;
    }

    @Override
    public QuizTest updateTest(QuizTest test) throws Exception {
        quizTestRepository.save(test);
        return test;
    }

    @Override
    public Boolean deleteTest(Long id) throws Exception {
        if(quizTestRepository.findById(id) != null) {
            quizTestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<QuizTest> findTests() {
        return quizTestRepository.findAll();
    }

    @Override
    public QuizTest findById(String id) throws Exception {
        return null;
    }

    @Override
    public QuizTest findByTestName(String testName) throws Exception {
        return null;
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
