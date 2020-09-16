package com.app.psicologia.controllers;

import com.app.psicologia.model.QuizTest;
import com.app.psicologia.model.ResponseTest;
import com.app.psicologia.model.User;
import com.app.psicologia.service.EmailServiceImpl;
import com.app.psicologia.service.QuizTestService;
import com.app.psicologia.service.ResponseTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

@RequestMapping(value = "/api", produces = { "application/json" })
public class TestController {

    @Autowired
    QuizTestService quizTestService;
    @Autowired
    ResponseTestService responseTestService;
    @Autowired
    EmailServiceImpl emailService;

    @RequestMapping(value = "/test/new", method = RequestMethod.POST, produces = { "application/json" })
    private boolean newTest (@RequestBody QuizTest test) throws Exception{
      boolean isCreateTest = false;
        QuizTest newTest = test;
        newTest.setId(quizTestService.getNextSequence("customSequences"));
        try {
            quizTestService.createTest(newTest);
            isCreateTest=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i=0; i< test.getQuestions().size();i++){
            System.out.println(test.getQuestions().get(i).getQuestionTitle());
            for (int j=0; j< test.getQuestions().get(i).getOptions().size();j++)
                System.out.println(test.getQuestions().get(i).getOptions().get(j).getOption());
        }
        return isCreateTest;
    }

    @RequestMapping(value = "/test/update", method = RequestMethod.PUT, produces = { "application/json" })
    public @ResponseBody QuizTest updateTest(@RequestBody QuizTest test) throws Exception {
        if (test != null) {
            System.out.println("update test");
            quizTestService.updateTest(test);

        }
        return test;
    }

    @RequestMapping(value = "/test/response", method = RequestMethod.POST, produces = { "application/json" })
    private boolean saveResponseTest (@RequestBody ResponseTest responses) throws Exception{
        boolean isSaveResponses = false;
        ResponseTest saveResponses = responses;
        saveResponses.setId(responseTestService.getNextSequence("customSequences"));
        try {
            responseTestService.saveResponses(saveResponses);
             isSaveResponses = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSaveResponses;
    }
    @RequestMapping(value = "/tests", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    List<QuizTest> listTests() throws Exception {
        List<QuizTest> listTest = quizTestService.findTests();
        return listTest;
    }
}
