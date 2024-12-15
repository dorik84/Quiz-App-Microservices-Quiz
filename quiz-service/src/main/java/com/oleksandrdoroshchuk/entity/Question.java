package com.oleksandrdoroshchuk.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String category;
    private String difficulty;
    private String option1;
    private String option2;
    private String option3;
    private String answer;
    
    @Column(name="question_title")
    private String questionTitle;

}