package com.oleksandrdoroshchuk.service;



import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.oleksandrdoroshchuk.dao.QuizDao;
import com.oleksandrdoroshchuk.entity.Question;
import com.oleksandrdoroshchuk.entity.Quiz;
import com.oleksandrdoroshchuk.model.QuestionWrapper;
import com.oleksandrdoroshchuk.model.Response;



@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;



    public ResponseEntity<Quiz> createQuiz( String category, Integer numQs, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQs);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return new ResponseEntity<Quiz>(quizDao.save(quiz),HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> qw = questions.stream().map(q-> new QuestionWrapper(q)).collect(Collectors.toList());
        return new ResponseEntity<List<QuestionWrapper>>(qw,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateScore(Integer quizId, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        questions.sort(Comparator.comparing(Question::getId));
        responses.sort(Comparator.comparing(Response::getId));
        Integer score=0;
     
        for (int i=0;i<responses.size();i++){
            if (responses.get(i).getResponse().equals(questions.get(i).getAnswer())){
                score++;
            }
        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }

}
