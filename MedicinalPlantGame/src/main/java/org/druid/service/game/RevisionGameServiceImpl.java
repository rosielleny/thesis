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
import java.util.stream.Collectors;

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

        System.out.println(quizGame);
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
                    case "Plant": question.setQuestionText(currentQuestion.replace("{Plant}", p.getPlantName()));
                                        break;
                    case "Ailment": question.setQuestionText(currentQuestion.replace("{Ailment}", p.getTreatmentFor()));
                                        break;
                    // If the question subject is PlantPicture, the subject becomes the plant's default picture as there is nothing to replace in the question text itself
                    case "PlantPicture":question.setQuestionSubjectType(p.getDefaultPicture());
                                        break;
                    default: question.setQuestionText(currentQuestion);
                        break;
                }
                switch (question.getQuestionAnswerType()) {
                    case "Plant": question.setAnswer(p.getPlantName());
                        break;
                    case "Ailment": question.setAnswer(p.getTreatmentFor());
                        break;
                    case "PlantPicture": question.setAnswer(p.getDefaultPicture());
                        break;
                    default: question.setAnswer("");
                         break;
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


        List<Plant> allPlants = plantService.getAllPlants();

        List<String> answers = new ArrayList<>();
        List<String> potentialAnswers = new ArrayList<>();

        // Getting a list of strings with potential answers
        for (Plant p : allPlants) {
            String potentialAnswer = null;
            switch(question.getQuestionAnswerType()) {
                case "Plant":
                    potentialAnswer = p.getPlantName();
                    break;
                case "Ailment":
                    potentialAnswer = p.getTreatmentFor();
                    break;
                case "PlantPicture":
                    potentialAnswer = p.getDefaultPicture();
                    break;
            }
            if (potentialAnswer != null && !potentialAnswer.equals(question.getAnswer())) {
                potentialAnswers.add(potentialAnswer);
            }
        }

        Collections.shuffle(potentialAnswers);
        answers.add(question.getAnswer());

        while(answers.size() < 4){

            String answer = potentialAnswers.get(0);
            if(answers.contains(answer)){
                potentialAnswers.remove(0);
            }
            else {
                answers.add(potentialAnswers.get(0));
                potentialAnswers.remove(0);
            }
        }

        question.setAllAnswers(answers);
        return question;
    }

}
