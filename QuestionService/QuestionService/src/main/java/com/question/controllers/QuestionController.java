package com.question.controllers;

import com.question.entities.Question;
import com.question.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //create
    @PostMapping
    public Question create(@RequestBody Question question)
    {
        return questionService.create(question);
    }

    //getOne
    @GetMapping("/{questionId}")
    public Question getOne(@PathVariable Long questionId)
    {
        return questionService.getOne(questionId);
    }

    //getAll
    @GetMapping
    public List<Question> getAll()
    {
        return questionService.getAll();
    }

    //findByQuizId
    @GetMapping("/quiz/{quizId}")
    public List<Question>getByQuizId(@PathVariable long quizId){
        return questionService.getByQuizId(quizId);
    }
}
