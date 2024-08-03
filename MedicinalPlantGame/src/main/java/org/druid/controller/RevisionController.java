package org.druid.controller;

import org.druid.entity.composite.QuestionGame;
import org.druid.entity.composite.QuizGame;
import org.druid.entity.original.QuestionTemplate;
import org.druid.entity.original.Quiz;
import org.druid.service.game.RevisionGameService;
import org.druid.service.microserviceCom.gameMechanics.RevisionServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@SessionAttributes("quizGame")
@Controller
public class RevisionController {

    // Dummy player for development purposes
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    RevisionServiceAgg revisionService;
    @Autowired
    RevisionGameService revisionGameService;

    @CrossOrigin
    @RequestMapping("/revision")
    public ModelAndView showRevisionPage(){

        ModelAndView mav = new ModelAndView("revision");
        List<Quiz> quizzes = revisionService.getAllQuizzes();

        mav.addObject("quizzes", quizzes);

        return mav;
    }

    @CrossOrigin
    @RequestMapping("/revision/quiz/{quizId}")
    public ModelAndView showQuizPage(@PathVariable("quizId") int quizId){

        ModelAndView mav = new ModelAndView("revisionQuiz");
        QuizGame quizGame = revisionGameService.getMixedQuizGame(DUMMY_PLAYER_ID, quizId);
        quizGame.setQuizProgress(quizGame.getQuizProgress() + 1);
        mav.addObject("quizGame", quizGame);
        return mav;

    }

    /* Function is called repeatedly to display each question in a quiz until the quiz is over*/
    @CrossOrigin
    @RequestMapping("/revision/quiz/next")
    public ModelAndView showNextQuestion(@ModelAttribute("quizGame") QuizGame quizGame,  @RequestParam String selectedAnswer){


        ModelAndView mav = new ModelAndView("revisionQuiz");

        // Only checking for correct answers if quiz progress is 1 or greater,
        // otherwise this must be the first time the function has been called, and so
        // the user has not answered questions yet.
        if(quizGame.getQuizProgress() >= 1){
            QuestionGame currentQuestion = quizGame.getQuizQuestions().get(quizGame.getQuizProgress() -1);
            String playerAnswer = currentQuestion.getAnswer();
            // Counting the answer if it is correct
            if(playerAnswer.equals(selectedAnswer)){
                quizGame.setCorrectAnswers(quizGame.getCorrectAnswers() + 1);
            }
        }

        if(quizGame.getQuizProgress() != quizGame.getQuizQuestions().size()) {
            quizGame.setQuizProgress(quizGame.getQuizProgress() + 1); // Tracking which question player is on
            mav.addObject("quizGame", quizGame);
            return mav;
        }
        else{
            ModelAndView mav2 = new ModelAndView("revisionResult");
            mav.addObject("quizGame", quizGame);
            return mav2;
        }

    }
}
