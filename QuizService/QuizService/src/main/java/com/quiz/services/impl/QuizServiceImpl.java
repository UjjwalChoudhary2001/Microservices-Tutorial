package com.quiz.services.impl;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

//    public QuizServiceImpl(QuizRepository quizRepository) {
//        this.quizRepository=quizRepository;
//    }

    QuestionClient questionClient;
    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getOne(Long id) {
        //return quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        Quiz quiz = quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }

    @Override
//    public List<Quiz> getAll() {
//        return quizRepository.findAll();
//    }
    public List<Quiz> getAll() {
        List<Quiz>quizzes =  quizRepository.findAll();

        /* ------------------Understanding of map()----------------------
        Function<Quiz,Quiz> fun = new Function<Quiz,Quiz>()
        {
            @Override
            public Quiz apply(Quiz quiz) {
                 quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
                 return quiz;
            }
        };

        lamda expression as Function is functional interface
        Function<Quiz,Quiz>fun = (quiz) -> {
                quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
                return quiz;
        };

        List<Quiz>newQuizList = quizzes.stream().map(fun).collect(Collectors.toList());
        */

        List<Quiz>newQuizList = quizzes.stream().map(quiz->{
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

        return newQuizList;
    }
}
