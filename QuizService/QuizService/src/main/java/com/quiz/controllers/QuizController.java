package com.quiz.controllers;

import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //create
    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }

    //getOne
    @GetMapping("/{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.getOne(id);
    }

    //getAll
    @GetMapping
    public List<Quiz> getAll()
    {
        return quizService.getAll();
    }
}
