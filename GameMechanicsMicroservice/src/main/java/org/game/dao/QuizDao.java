package org.game.dao;

import org.game.entity.QuestionTemplate;
import org.game.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* This DAO retrieves data for Question and Quiz*/
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

    @Query("FROM Quiz WHERE quizId = :quizId")
    Optional<Quiz> findQuizById(@Param("quizId")int quizId);
}