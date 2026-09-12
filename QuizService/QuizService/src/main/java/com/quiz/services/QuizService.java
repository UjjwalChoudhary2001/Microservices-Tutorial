package com.quiz.services;

import com.quiz.entities.Quiz;

import java.util.List;

public interface QuizService {
    Quiz add(Quiz quiz);
    Quiz getOne(Long id);
    List<Quiz> getAll();
}
