package com.oleksandrdoroshchuk.quiz_service.service;





import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.oleksandrdoroshchuk.quiz_service.dao.QuizDao;
import com.oleksandrdoroshchuk.quiz_service.entity.Quiz;
import com.oleksandrdoroshchuk.quiz_service.feign.QuizInterface;
import com.oleksandrdoroshchuk.quiz_service.model.QuestionStripper;
import com.oleksandrdoroshchuk.quiz_service.model.Response;





@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<Quiz> createQuiz( String category, Integer numQs, String title) {
        List<Integer> questionIds = quizInterface.getQuizQuestions(category,numQs).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        return new ResponseEntity<Quiz>(quizDao.save(quiz),HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionStripper>> getQuestionsByQuizId(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);
    }



    public ResponseEntity<Integer> calculateScore( List<Response> responses) {

        return quizInterface.calculateScore(responses);
    }

}
