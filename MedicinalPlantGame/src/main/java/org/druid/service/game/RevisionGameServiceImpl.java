package org.druid.service.game;

import org.druid.entity.composite.QuestionGame;
import org.druid.entity.composite.QuizGame;
import org.druid.entity.original.Plant;
import org.druid.entity.original.PlayerPlant;
import org.druid.entity.original.QuestionTemplate;
import org.druid.entity.original.Quiz;
import org.druid.entity.original.key.PlayerPlantKey;
import org.druid.service.microserviceCom.gameMechanics.RevisionServiceAgg;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerPlantServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class RevisionGameServiceImpl implements RevisionGameService {

    @Autowired
    RevisionServiceAgg revisionService;
    @Autowired
    PlayerPlantServiceAgg playerPlantService;
    @Autowired
    PlantServiceAgg plantService;

    @Override
    public QuizGame getMixedQuizGame(int playerId, int quizId) {

        Quiz quiz = revisionService.getQuizById(quizId);
        QuizGame quizGame = new QuizGame();

        // Getting necessary lists, for this function it is all plants and all question templates
        List<PlayerPlant> playerPlants = playerPlantService.getPlayerPlants(playerId);
        List<QuestionTemplate> questionTemplates = revisionService.getAllQuestions();
        List<Plant> randomTestPlants = getRandomPlants(quiz.getQuestionNumber(), playerId, playerPlants);

        // Calling other function to choose the questions
        List<QuestionGame> questions = formulateQuestions(questionTemplates, randomTestPlants);

        quizGame.setQuizGameAttributesFromQuiz(quiz); // Using the quiz object to fill out most values
        quizGame.setQuizQuestions(questions); // Setting the questions

        return quizGame;
    }

    /* Provides a list of random plants from the plants the player has discovered.
    * These will be used as question subjects*/
    private List<Plant> getRandomPlants(int questionNumber, int playerId, List<PlayerPlant> playerPlants){


        // Next, we must select random plants for the questions from the player's plants
        List<Plant> randomTestPlants = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < questionNumber; i++) {

            // Getting a random index to select a random PlayerPlant from the list
            int randomIndex = random.nextInt(playerPlants.size());
            // Using the random index to select the PlayerPlant
            PlayerPlant playerPlant = playerPlants.get(randomIndex);
            // Getting the key from the PlayerPlant
            PlayerPlantKey key = playerPlant.getPlayerPlantKey();
            // Using the key to get the plant ID, and using that to get the plant
            Plant plant = plantService.getPlantById(key.getPlantId());
            // Adding the plant to the test plants list
            randomTestPlants.add(plant);
        }

        return randomTestPlants;
    }

    /* Edits the question text itself with the appropriate plant name */
    private List<QuestionGame> formulateQuestions(List<QuestionTemplate> questionTemplates, List<Plant> randomTestPlants) {

        List<QuestionGame> allQuestions = new ArrayList<>();

       // Generating all possible questions for these plants
        for(Plant p : randomTestPlants){

            for(QuestionTemplate qt : questionTemplates){

                QuestionGame question = new QuestionGame();
                question.setQuestionGameAttributesFromQuestionTemplate(qt);

                String currentQuestion = question.getQuestionText();

                switch (question.getQuestionSubjectType()) {
                    case "Plant" -> question.setQuestionText(currentQuestion.replace("{Plant}", p.getPlantName()));

                    case "Ailment" -> question.setQuestionText(currentQuestion.replace("{Ailment}", p.getTreatmentFor()));

                    // If the question subject is PlantPicture, the subject becomes the plant's default picture as there is nothing to replace in the question text itself
                    case "PlantPicture" -> question.setQuestionSubjectType(p.getDefaultPicture());

                    default -> question.setQuestionText(currentQuestion);
                }
                switch (question.getQuestionAnswerType()) {
                    case "Plant" -> question.setAnswer(p.getPlantName());
                    case "Ailment" -> question.setAnswer(p.getTreatmentFor());
                    case "PlantPicture" -> question.setAnswer(p.getDefaultPicture());
                    default -> question.setAnswer("");
                }
                question = addWrongAnswers(question);

                allQuestions.add(question);
            }
        }

        // Now a number of questions will be selected, based on the length the quiz is supposed to be
        List<Integer> numbers = new ArrayList<>(); // This list represents all the indices of the questions list
        for(int j  = 0; j < allQuestions.size(); j++ ){
            numbers.add(j);
        }
        Collections.shuffle(numbers); // Shuffling them

        List<QuestionGame> chosenQuestions = new ArrayList<>();
        // Using the first [quiz length] numbers as indices to pick random questions. RandomTestPlants list will be the same size as the quiz should be
        for(int i = 0; i < randomTestPlants.size(); i++){

            chosenQuestions.add(allQuestions.get(numbers.get(i))); // Add to chosen questions the question at the index matching the number at i index in numbers
        }

        return chosenQuestions;
    }

    private QuestionGame addWrongAnswers(QuestionGame question) {
        Random random = new Random();

        // Finally, each question should have three wrong answers
        List<String> wrongAnswers = new ArrayList<>();
        // We take these from any plant in the plant table
        List<Plant> allPlants = plantService.getAllPlants();

        for(int i = 1; i <=3; i++ ) {
            int randomIndex = random.nextInt(allPlants.size());

            String wrongAnswer = "";

            switch (question.getQuestionAnswerType()) {
                case "Plant" -> wrongAnswer = allPlants.get(randomIndex).getPlantName();
                case "Ailment" -> wrongAnswer = allPlants.get(randomIndex).getTreatmentFor();
                case "PlantPicture" -> wrongAnswer = allPlants.get(randomIndex).getDefaultPicture();
                default -> wrongAnswer = "";
            }

            // Prevents duplicate wrong answers or wrong answers which match the real answer
            if (wrongAnswers.contains(wrongAnswer) || wrongAnswers.contains(question.getAnswer())) {
                i--;
            } else {
                wrongAnswers.add(wrongAnswer);
            }
        }
        return question;
    }

}
