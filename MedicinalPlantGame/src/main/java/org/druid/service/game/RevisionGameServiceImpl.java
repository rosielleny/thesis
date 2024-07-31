package org.druid.service.game;

import org.druid.entity.composite.QuizGame;
import org.druid.entity.original.Plant;
import org.druid.entity.original.PlayerPlant;
import org.druid.entity.original.Question;
import org.druid.entity.original.Quiz;
import org.druid.service.microserviceCom.gameMechanics.RevisionServiceAgg;
import org.druid.service.microserviceCom.player.PlayerPlantServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RevisionGameServiceImpl implements RevisionGameService {

    @Autowired
    RevisionServiceAgg revisionService;
    @Autowired
    PlayerPlantServiceAgg playerPlantService;

    @Override
    public QuizGame getQuizGame(int playerId, int quizId) {

        Quiz quiz = revisionService.getQuizById(quizId);
        QuizGame quizGame = new QuizGame();

        List<Question> questions = chooseQuizGameQuestions(playerId, quiz);
        return null;
    }

    public List<Question> chooseQuizGameQuestions(int playerId, Quiz quiz) {

        // First, we must get player plants so the questions only contain discovered plants

        List<PlayerPlant> playerPlants = playerPlantService.getPlayerPlants(playerId);

        // Next, we must select random plants for the questions from the player's plants
        List<PlayerPlant> randomTestPlants = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < quiz.getQuestionNumber(); i++) {

            int randomIndex = random.nextInt(playerPlants.size());
            randomTestPlants.add(playerPlants.get(randomIndex));
        }

        

    }
}
