package com.app.psicologia.controllers;

import com.app.psicologia.model.Studio;
import com.app.psicologia.service.EmailServiceImpl;
import com.app.psicologia.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping(value = "/api", produces = { "application/json" })
public class StudioController {
    @Autowired
    StudioService studioService;

    @RequestMapping(value = "/studio/new", method = RequestMethod.POST, produces = { "application/json" })
    public @ResponseBody
    Boolean createStudio(@RequestBody Studio studio) throws Exception{
        if (studio != null) {
            studio.setId(studioService.getNextSequence("customSequences"));
            studioService.createStudio(studio);
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/studio/update", method = RequestMethod.POST, produces = { "application/json" })
    public @ResponseBody
    Boolean updateStudio(@RequestBody Studio studio) throws Exception{
        if (studio != null) {
            studioService.createStudio(studio);
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/studios", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody List<Studio> searchStudios() throws Exception{
        return studioService.findStudios();
    }

}
