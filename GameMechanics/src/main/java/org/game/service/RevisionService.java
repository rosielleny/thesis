package org.game.service;

import org.game.entity.Question;
import org.game.entity.Quiz;

import java.util.List;

public interface RevisionService {

    List<Question> getAllQuestions();
    Quiz getQuizById(int quizId);
}
