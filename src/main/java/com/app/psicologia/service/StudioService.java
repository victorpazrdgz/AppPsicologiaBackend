package com.app.psicologia.service;

import com.app.psicologia.model.Studio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudioService {
    public Studio createStudio(Studio studio) throws Exception;
    public List<Studio> findStudios() throws Exception;
    public Long getNextSequence(String seqNumber);
}
