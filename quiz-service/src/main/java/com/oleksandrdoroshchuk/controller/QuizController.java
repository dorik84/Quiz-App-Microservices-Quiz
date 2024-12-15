package com.oleksandrdoroshchuk.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleksandrdoroshchuk.entity.Quiz;
import com.oleksandrdoroshchuk.model.QuestionWrapper;
import com.oleksandrdoroshchuk.model.QuizDto;
import com.oleksandrdoroshchuk.model.Response;
import com.oleksandrdoroshchuk.service.QuizService;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto dto ) {
        // return new ResponseEntity<>("Hello", HttpStatus.OK);
        return quizService.createQuiz(dto.getCategory(),dto.getNumQuestions(),dto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(@PathVariable Integer id) {
        return quizService.getQuestionsByQuizId(id);
    }
    
    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuizAnswers(@PathVariable Integer quizId, @RequestBody List<Response> responses) {
        System.out.println("submitQuizAnswers triggered");
        return quizService.calculateScore(quizId,responses);
    }
    
    
}
