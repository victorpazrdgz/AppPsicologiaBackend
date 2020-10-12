package com.app.psicologia.repository;

import com.app.psicologia.model.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudioRepository extends MongoRepository<Studio,Long> {

}
