package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.Question;
import org.druid.entity.original.Quiz;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizGame {

    private int quizId;
    private boolean isExam;
    private int xpWorth;

    private List<Question> quizQuestions;

    public void setQuizGameAttributesFromQuiz(Quiz quiz) {
        this.quizId = quiz.getQuizId();
        this.isExam = quiz.isExam();
        this.xpWorth = quiz.getXpWorth();
    }
}
