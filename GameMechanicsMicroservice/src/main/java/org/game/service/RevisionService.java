package org.game.service;

import org.game.entity.QuestionTemplate;
import org.game.entity.Quiz;

import java.util.List;

public interface RevisionService {

    List<QuestionTemplate> getAllQuestions();
    Quiz getQuizById(int quizId);
    List<Quiz> getQuizzes();
}
