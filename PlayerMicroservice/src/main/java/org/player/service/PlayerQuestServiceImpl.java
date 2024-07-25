package org.player.service;

import org.player.dao.PlayerQuestDao;
import org.player.entity.PlayerQuest;
import org.player.entity.key.PlayerQuestKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerQuestServiceImpl implements PlayerQuestService {

    @Autowired
    private PlayerQuestDao playerQuestDao;


    public PlayerQuest savePlayerQuest(PlayerQuest playerQuest) {
        return playerQuestDao.save(playerQuest);
    }

    public List<PlayerQuest> getPlayerQuestsByPlayerId(int playerId) {
        return playerQuestDao.findByPlayerId(playerId);
    }

    public PlayerQuest getPlayerQuestById(int playerId, int questId) {
        PlayerQuestKey key = new PlayerQuestKey();
        key.setPlayerId(playerId);
        key.setQuestId(questId);
        return playerQuestDao.findById(key).orElse(null);
    }
}
