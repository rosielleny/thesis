package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.Question;
import org.druid.entity.original.Quiz;

import java.util.List;

public interface RevisionServiceAgg {

    List<Question> getAllQuestions();
    Quiz getQuizById(int quizId);
}
