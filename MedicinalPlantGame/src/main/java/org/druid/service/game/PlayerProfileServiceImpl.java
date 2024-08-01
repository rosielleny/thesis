package org.druid.service.game;

import org.druid.entity.composite.PlayerProfile;
import org.druid.entity.original.Player;
import org.druid.entity.original.PlayerAntidote;
import org.druid.entity.original.PlayerQuest;
import org.druid.service.microserviceCom.player.PlayerAntidoteServiceAgg;
import org.druid.service.microserviceCom.player.PlayerPlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.druid.service.microserviceCom.player.PlayerServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerProfileServiceImpl implements PlayerProfileService {

    @Autowired
    PlayerServiceAgg playerService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;
    @Autowired
    PlayerPlantServiceAgg playerPlantService;
    @Autowired
    PlayerAntidoteServiceAgg playerAntidoteService;

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
}
