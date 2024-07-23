package org.game.resource;

import org.game.entity.Antidote;
import org.game.entity.Question;
import org.game.entity.Quiz;
import org.game.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class RevisionResourceImpl implements RevisionResource {

    @Autowired
    private RevisionService revisionService;

    // Get all Questions
    @CrossOrigin
    @GetMapping(path = "/revision/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getAllQuestions() {

        List<Question> questions = revisionService.getAllQuestions();

        if(questions != null){
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Question>>(questions, HttpStatus.NO_CONTENT);
        }
    }

    // Get Quiz by id
    @CrossOrigin
    @GetMapping(path = "/revision/quiz/{quizId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quiz> getQuizById(@PathVariable int quizId) {

        Quiz quiz = revisionService.getQuizById(quizId);

        if(quiz != null){
            return new ResponseEntity<Quiz>(quiz, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Quiz>(quiz, HttpStatus.NO_CONTENT);
        }
    }
}
