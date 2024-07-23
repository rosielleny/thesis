package org.game.resource;

import org.game.entity.GameCharacter;
import org.game.entity.GameLevel;
import org.game.entity.Quest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface QuestResource {

    ResponseEntity<Quest> getQuestById(@PathVariable int questId);
    ResponseEntity<GameCharacter> getCharacterById(@PathVariable int characterId);
    ResponseEntity<GameLevel> getLevelById(@PathVariable int levelId);
}
