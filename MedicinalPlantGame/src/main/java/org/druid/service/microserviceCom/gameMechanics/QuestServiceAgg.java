package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.GameCharacter;
import org.druid.entity.original.GameLevel;
import org.druid.entity.original.Quest;

import java.util.List;


public interface QuestServiceAgg {

    Quest getQuestByQuestId(int questId);
    GameCharacter getGameCharacterById(int gameCharacterId);
    GameLevel getGameLevelById(int gameLevelId);
    List<Quest> getQuestsByGameLevelId(int gameLevelId);

}
