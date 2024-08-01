package org.druid.service.game;

import org.druid.entity.composite.QuizGame;

public interface RevisionGameService {

    QuizGame getMixedQuizGame(int playerId, int quizId);
}
