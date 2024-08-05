package org.druid.service.game;

import org.druid.entity.composite.PlayerProfile;
import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.*;
import org.druid.entity.original.key.PlayerAntidoteKey;
import org.druid.entity.original.key.PlayerPlantKey;
import org.druid.entity.original.key.PlayerQuestKey;
import org.druid.service.microserviceCom.gameMechanics.AntidoteServiceAgg;
import org.druid.service.microserviceCom.gameMechanics.QuestServiceAgg;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerAntidoteServiceAgg;
import org.druid.service.microserviceCom.player.PlayerPlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.druid.service.microserviceCom.player.PlayerServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerGameServiceImpl implements PlayerGameService {

    @Autowired
    PlayerServiceAgg playerService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;
    @Autowired
    PlayerPlantServiceAgg playerPlantService;
    @Autowired
    PlayerAntidoteServiceAgg playerAntidoteService;
    @Autowired
    PlantServiceAgg plantService;
    @Autowired
    AntidoteServiceAgg antidoteService;
    @Autowired
    QuestServiceAgg questService;
    @Autowired
    QuestGameService questGameService;

    /* Returns a composite entity that can be used for displaying the player's
    * profile information */
    @Override
    public PlayerProfile getPlayerProfile(int playerId) {

        Player player = playerService.getPlayer(playerId);

        PlayerProfile pProfile = new PlayerProfile();
        pProfile.setProfileWithPlayer(player); // Setting the information in the Player table to PlayerProfile

        // Calling other functions to calculate these stats
        pProfile.setQuestsCompleted(countQuestsCompleted(playerId));
        pProfile.setPagesUnlocked(countPagesUnlocked(playerId));
        pProfile.setAntidotesMade(calculateAntidotesMade(playerId));

        return pProfile;
    }

    @Override
    public List<Plant> getPlayersPlants(int playerId) {

        List<Plant> playersDiscoveredPlants = new ArrayList<>();
        List<PlayerPlant> playerPlants = playerPlantService.getPlayerPlants(playerId);

        for (PlayerPlant playerPlant : playerPlants) {
            PlayerPlantKey key = playerPlant.getPlayerPlantKey();

            Plant plant = plantService.getPlantById(key.getPlantId());

            playersDiscoveredPlants.add(plant);
        }

        return playersDiscoveredPlants;
    }

    @Override
    public List<Antidote> getPlayersAntidotes(int playerId) {

        List<Antidote> playersDiscoveredAntidotes = new ArrayList<>();
        List<PlayerAntidote> playerAntidotes = playerAntidoteService.getPlayerAntidotesByPlayerId(playerId);

        for (PlayerAntidote playerAntidote : playerAntidotes) {
            PlayerAntidoteKey key = playerAntidote.getPlayerAntidoteKey();

            Antidote antidote = antidoteService.getAntidoteById(key.getAntidoteId());

            playersDiscoveredAntidotes.add(antidote);
        }

        return playersDiscoveredAntidotes;
    }

    @Override
    public List<QuestGame> getPlayersQuests(int playerId) {

        List<QuestGame> playersDiscoveredQuests = new ArrayList<>();
        List<PlayerQuest> playerQuests = playerQuestService.getPlayerQuestsByPlayerId(playerId);

        for (PlayerQuest playerQuest : playerQuests) {
            PlayerQuestKey key = playerQuest.getPlayerQuestKey();

            QuestGame quest = questGameService.getQuestGame(key.getQuestId(), playerId);

            playersDiscoveredQuests.add(quest);
        }

        return playersDiscoveredQuests;
    }

    /* Function called by getPlayerProfile to count the number of quests
    * that the player has completed*/
    private int countQuestsCompleted(int playerId){

        int questsCompleted = 0;
        // Gets PlayerQuest objects based on the player's id and counts how many have the Completed status
        for(PlayerQuest pQ : playerQuestService.getPlayerQuestsByPlayerId(playerId)){

            if(pQ.getQuestStatus().equals("Complete")){
                questsCompleted++;
            }
        }

        return questsCompleted;
    }

    /* Called by getPlayerProfile to count the number of compendium pages
    * the player has unlocked. This will match the number of plants the player
    * has discovered, so for this we check the PlayerPlants table*/
    private int countPagesUnlocked(int playerId){

        return playerPlantService.getPlayerPlants(playerId).size();
    }

    /* Called by getPlayerProfile to calculate the number of antidotes the player has made.
    * This will be total antidotes made, including if the player made multiple of one antidote*/
    private int calculateAntidotesMade(int playerId){

        int antidotesMade = 0;

        for(PlayerAntidote pA : playerAntidoteService.getPlayerAntidotesByPlayerId(playerId)){

            antidotesMade += pA.getNumberMade();
        }

        return antidotesMade;
    }

    /* A plant is not unlocked without also unlocking a new antidote, so these are triggered
    * at the same time. They are unlocked by adding new entries in the PlayerPlant and PlayerAntidote
    * tables*/
    public void addNewPlayerPlantAndAntidote(int plantId, int playerId, int questId){

        PlayerPlantKey key = new PlayerPlantKey(playerId, plantId);
        PlayerPlant plantUnlocked = new PlayerPlant();
        plantUnlocked.setPlayerPlantKey(key);

        int lastDiscovered = 0;
        for(PlayerPlant p: playerPlantService.getPlayerPlants(playerId)){

            if(p.getDiscoveredOrder() > lastDiscovered){

                lastDiscovered = p.getDiscoveredOrder();
            }
        }

        plantUnlocked.setDiscoveredOrder(lastDiscovered + 1);

        playerPlantService.savePlayerPlant(plantUnlocked);
        addNewPlayerAntidote(questId, playerId);
    }

    /*Updates the quest stage when player completes a stage*/
    @Override
    public void updateQuestStage(int questId, int playerId, String stage) {
        PlayerQuest pq = playerQuestService.getPlayerQuestById(playerId, questId);
        pq.setQuestStage(stage);
        System.out.println(pq);
        playerQuestService.savePlayerQuest(pq);
    }

    /*Called by addNewPlayerPlantAndAntidote to handle the antidote portion
    * of the save*/
    private void addNewPlayerAntidote(int questId, int playerId){

        Quest quest = questService.getQuestByQuestId(questId);
        int antidoteId = quest.getAntidoteId();

        PlayerAntidoteKey key = new PlayerAntidoteKey(playerId, antidoteId);
        PlayerAntidote antidoteUnlocked = new PlayerAntidote();
        antidoteUnlocked.setPlayerAntidoteKey(key);

        antidoteUnlocked.setNumberUsed(0);
        antidoteUnlocked.setNumberMade(0);

        playerAntidoteService.savePlayerAntidote(antidoteUnlocked);
    }
}
