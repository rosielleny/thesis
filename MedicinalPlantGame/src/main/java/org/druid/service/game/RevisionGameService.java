package org.druid.service.game;

import org.druid.entity.composite.QuizGame;

public interface RevisionGameService {

    QuizGame getQuizGame(int playerId, int quizId);
}
