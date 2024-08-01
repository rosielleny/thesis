package org.druid.service.game;

import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.GameCharacter;
import org.druid.entity.original.PlayerQuest;
import org.druid.entity.original.Quest;
import org.druid.service.microserviceCom.gameMechanics.QuestServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestGameServiceImpl implements QuestGameService {

    @Autowired
    QuestServiceAgg questService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;


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

        return game;

    }

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
