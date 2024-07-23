package org.game.resource;

import org.game.entity.Question;
import org.game.entity.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RevisionResource {

    ResponseEntity<List<Question>> getAllQuestions();
    ResponseEntity<Quiz> getQuizById(@PathVariable int quizId);
}
