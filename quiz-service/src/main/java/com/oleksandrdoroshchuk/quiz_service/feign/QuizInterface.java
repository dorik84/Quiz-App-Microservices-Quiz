package com.oleksandrdoroshchuk.quiz_service.feign;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.oleksandrdoroshchuk.quiz_service.model.QuestionStripper;
import com.oleksandrdoroshchuk.quiz_service.model.Response;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuizQuestions(@RequestParam String category, @RequestParam Integer numQuestions );

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionStripper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    
    
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);
}
