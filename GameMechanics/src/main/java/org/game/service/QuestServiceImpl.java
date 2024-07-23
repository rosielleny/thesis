package org.game.service;

import org.game.dao.QuestDao;
import org.game.entity.GameCharacter;
import org.game.entity.GameLevel;
import org.game.entity.Quest;
import org.game.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestDao questDao;

    public Quest getQuestByQuestId(int questId) {
        return questDao.findById(questId).orElse(null);
    }

    @Override
    public GameCharacter getGameCharacterById(int gameCharacterId) {
        return questDao.findByGameCharacterId(gameCharacterId).orElse(null);
    }

    public GameLevel getGameLevelById(int gameLevelId) {
        return questDao.findByGameLevelId(gameLevelId).orElse(null);
    }
}
