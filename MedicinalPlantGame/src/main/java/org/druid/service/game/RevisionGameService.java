package org.druid.service.game;

import org.druid.entity.composite.QuizGame;

public interface RevisionGameService {

    QuizGame getMixedQuizGame(int playerId, int quizId);
    void awardQuizXP(int xpWorth, int totalQuestions, int playerId, int correctAnswers);
    void canPlayerDoLevelUpExam(int playerId);
    boolean hasPlayerPassedExam(int playerId, int correctAnswers, int questionNumber);
}
