package com.oleksandrdoroshchuk.quiz_service.model;



import com.oleksandrdoroshchuk.quiz_service.entity.Question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStripper {
    private Integer id;

    private String option1;
    private String option2;
    private String option3;
    private String questionTitle;

    public QuestionStripper(Question question){
        this.id = question.getId();
        this.option1 = question.getOption1();
        this.option2 = question.getOption2();
        this.option3 = question.getOption3();
        this.questionTitle = question.getQuestionTitle();
    }
}
