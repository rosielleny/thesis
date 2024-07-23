package org.game.service;

import org.game.entity.GameCharacter;
import org.game.entity.GameLevel;
import org.game.entity.Quest;


public interface QuestService {

    Quest getQuestByQuestId(int questId);
    GameCharacter getGameCharacterById(int gameCharacterId);
    GameLevel getGameLevelById(int gameLevelId);

}
