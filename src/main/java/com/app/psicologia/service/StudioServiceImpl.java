package com.app.psicologia.service;

import com.app.psicologia.model.CustomSequences;
import com.app.psicologia.model.Studio;
import com.app.psicologia.repository.StudioRepository;
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
public class StudioServiceImpl implements StudioService {
    @Autowired
    StudioRepository studioRepository;
    @Autowired
    private MongoOperations mongo;
    @Override
    public Studio createStudio(Studio studio) throws Exception {
        return studioRepository.save(studio);
    }

    @Override
    public List<Studio> findStudios() throws Exception {
        return studioRepository.findAll();
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
