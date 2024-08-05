package org.druid.service.game;

import org.druid.entity.composite.AntidoteGame;
import org.druid.entity.composite.PlayerProfile;
import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.Antidote;
import org.druid.entity.original.Plant;
import org.druid.entity.original.Quest;

import java.util.List;

public interface PlayerGameService {

    PlayerProfile getPlayerProfile(int playerId);
    List<Plant> getPlayersPlants(int playerId);
    List<Antidote> getPlayersAntidotes(int playerId);
    List<QuestGame> getPlayersQuests(int playerId);
    void addNewPlayerPlantAndAntidote(int plantId, int playerId, int questId);
    void updateQuestStage(int questId,int playerId, String stage);
}
