package org.druid.service.game;

import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.*;
import org.druid.entity.original.key.PlayerQuestKey;
import org.druid.service.microserviceCom.gameMechanics.AntidoteServiceAgg;
import org.druid.service.microserviceCom.gameMechanics.QuestServiceAgg;
import org.druid.service.microserviceCom.player.PlayerAntidoteServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.druid.service.microserviceCom.player.PlayerServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestGameServiceImpl implements QuestGameService {

    @Autowired
    QuestServiceAgg questService;
    @Autowired
    AntidoteServiceAgg antidoteService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;
    @Autowired
    PlayerAntidoteServiceAgg playerAntidoteService;
    @Autowired
    PlayerServiceAgg playerService;
    @Autowired
    RevisionGameServiceImpl revisionGameService;


    /* Assembles the QuestGame object by gathering data from various services*/
    @Override
    public QuestGame getQuestGame(int questId, int playerId) {

        Quest quest = questService.getQuestByQuestId(questId);
        QuestGame game = new QuestGame();
        game.setValuesUsingQuest(quest);

        // Getting Quest Giver information
        GameCharacter questGiver = questService.getGameCharacterById(quest.getQuestGiverId());
        game.setQuestGiverName(questGiver.getGameCharacterName());
        game.setQuestGiverPicture(questGiver.getGameCharacterPicture());

        // Getting Patient information
        GameCharacter patient = questService.getGameCharacterById(quest.getPatientId());
        game.setPatientName(questService.getGameCharacterById(quest.getPatientId()).getGameCharacterName());
        game.setPatientPicture(questService.getGameCharacterById(quest.getPatientId()).getGameCharacterPicture());

        // Getting player progress information
        game.setPlayerQuestStatus(getPlayerQuestStatusOrStage(questId, playerId, true));
        game.setPlayerStage(getPlayerQuestStatusOrStage(questId, playerId, false));
        // Quests are currently named by the name of the antidote
        game.setQuestName(antidoteService.getAntidoteById(game.getAntidoteId()).getAntidoteName());

        return game;

    }

    /* Called by the front end to verify that the player is allowed to complete this quest*/
    @Override
    public Boolean completedQuest(int questId, int playerId) {
        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(playerId, questId);
        Quest quest = questService.getQuestByQuestId (questId);
        int antidoteId = quest.getAntidoteId();

        PlayerAntidote playerAntidote = playerAntidoteService.getPlayerAntidoteById(playerId, antidoteId);

        // If the number of antidotes the player has made minus the number they have used is greater than 0, then they have made an antidote that can be used in this quest
        if(playerAntidote.getNumberMade() - playerAntidote.getNumberUsed() > 0) {
            // Updating use as returning true will trigger the consumption of this antidote
            playerAntidote.setNumberUsed(playerAntidote.getNumberUsed() +1);
            playerAntidoteService.savePlayerAntidote(playerAntidote);
            // Updating quest status as quest is now complete
            playerQuest.setQuestStatus("Complete");
            playerQuestService.savePlayerQuest(playerQuest);

            awardQuestXP(quest.getXpValue(), playerId);
            return true;
        }
        else{
            return false;
        }
    }

    /* Function called whenever a quest is completed to determine if the quest directly
    * after it should be unlocked*/
    @Override
    public void unlockQuestsAfterLevelUp(int playerId) {

        Player player = playerService.getPlayer(playerId);
        List<Quest> quests = questService.getQuestsByGameLevelId(player.getPlayerLevel());

        for(Quest quest : quests) {
            // Then we can unlock a new quest by making a new PlayerQuest object
            PlayerQuest playerQuest = new PlayerQuest();
            PlayerQuestKey key = new PlayerQuestKey();
            playerQuest.setQuestStage("Beginning");
            playerQuest.setQuestStatus("Inactive");
            key.setPlayerId(playerId);
            key.setQuestId(quest.getQuestId());
            playerQuest.setPlayerQuestKey(key);

            playerQuestService.savePlayerQuest(playerQuest);

        }

    }

    private void awardQuestXP(int questXP, int playerId) {

        Player player = playerService.getPlayer(playerId);
        player.setPlayerTotalXP(player.getPlayerTotalXP() + questXP);
        playerService.savePlayer(player);
        // XP has been awarded, so it is possible a level up exam is available
        revisionGameService.canPlayerDoLevelUpExam(playerId);
    }

    /* Used by the getQuestGame function to get these aspects*/
    private String getPlayerQuestStatusOrStage(int questId, int playerId, boolean isStatus) {
        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(playerId, questId);

        if(isStatus){
            return playerQuest.getQuestStatus();
        }
        else{
            return playerQuest.getQuestStage();
        }
    }


}
