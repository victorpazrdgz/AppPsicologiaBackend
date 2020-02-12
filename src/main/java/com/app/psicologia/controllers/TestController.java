package com.app.psicologia.controllers;

import com.app.psicologia.model.QuizTest;
import com.app.psicologia.model.User;
import com.app.psicologia.service.QuizTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

@RequestMapping(value = "/api", produces = { "application/json" })
public class TestController {

    @Autowired
    QuizTestService quizTestService;

    @RequestMapping(value = "/test/new", method = RequestMethod.POST, produces = { "application/json" })
    private boolean newTest (@RequestBody QuizTest test) throws Exception{
      boolean isCreateTest = false;
        QuizTest newTest = test;
        newTest.setId(quizTestService.getNextSequence("customSequences"));
        quizTestService.createTest(newTest);
        for (int i=0; i< test.getQuestions().size();i++){
            System.out.println(test.getQuestions().get(i).getQuestionTitle());
            for (int j=0; j< test.getQuestions().get(i).getOptions().size();j++)
                System.out.println(test.getQuestions().get(i).getOptions().get(j).getOption());
        }
        return isCreateTest;
    }
}
