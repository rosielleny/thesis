package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.QuestionTemplate;
import org.druid.entity.original.Quiz;

import java.util.List;

public interface RevisionServiceAgg {

    List<QuestionTemplate> getAllQuestions();
    Quiz getQuizById(int quizId);
    List<Quiz> getAllQuizzes();
}
