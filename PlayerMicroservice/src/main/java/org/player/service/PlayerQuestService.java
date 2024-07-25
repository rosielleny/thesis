package org.player.service;

import org.player.entity.PlayerQuest;

import java.util.List;

public interface PlayerQuestService {

    // Create and Update
    public PlayerQuest savePlayerQuest(PlayerQuest playerQuest);
    // Read
    public List<PlayerQuest> getPlayerQuestsByPlayerId(int playerId);
    public PlayerQuest getPlayerQuestById(int playerId, int questId);
}
