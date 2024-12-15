package com.oleksandrdoroshchuk.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oleksandrdoroshchuk.entity.Quiz;



@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
