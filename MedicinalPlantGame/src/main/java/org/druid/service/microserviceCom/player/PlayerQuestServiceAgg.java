package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerQuest;

import java.util.List;

public interface PlayerQuestServiceAgg {

    // Create and Update
    public PlayerQuest savePlayerQuest(PlayerQuest playerQuest);
    // Read
    public List<PlayerQuest> getPlayerQuestsByPlayerId(int playerId);
    public PlayerQuest getPlayerQuestById(int playerId, int questId);
}
