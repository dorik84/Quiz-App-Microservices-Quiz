package com.oleksandrdoroshchuk.quiz_service.controller;







import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleksandrdoroshchuk.quiz_service.entity.Quiz;
import com.oleksandrdoroshchuk.quiz_service.model.QuizDto;
import com.oleksandrdoroshchuk.quiz_service.service.QuizService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path="quiz")
@Slf4j
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto dto ) {
        return quizService.createQuiz(dto.getCategory(),dto.getNumQuestions(),dto.getTitle());
    }

    @GetMapping("test")
    public String getMethodName( ) {
        return new String("Test!!!");
    }
    

    // @GetMapping("get/{id}")
    // public ResponseEntity<List<QuestionStripper>> getQuestionsByQuizId(@PathVariable Integer id) {
    //     return quizService.getQuestionsByQuizId(id);
    // }
    
    // @PostMapping("submit/{quizId}")
    // public ResponseEntity<Integer> submitQuizAnswers(@PathVariable Integer quizId, @RequestBody List<Response> responses) {
    //     System.out.println("submitQuizAnswers triggered");
    //     return quizService.calculateScore(quizId,responses);
    // }
    
    
}
