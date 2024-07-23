package org.game.resource;

import org.game.entity.GameCharacter;
import org.game.entity.GameLevel;
import org.game.entity.Quest;
import org.game.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestResourceImpl implements QuestResource {

    @Autowired
    private QuestService questService;

    @CrossOrigin
    @GetMapping(path = "/quest/{questId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quest> getQuestById(int questId) {

        Quest quest = questService.getQuestByQuestId(questId);

        if(quest != null){
            return new ResponseEntity<Quest>(quest, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Quest>(quest, HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/quest/character/{gameCharacterId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameCharacter> getCharacterById(int gameCharacterId) {

        GameCharacter gameCharacter = questService.getGameCharacterById(gameCharacterId);

        if(gameCharacter != null){
            return new ResponseEntity<GameCharacter>(gameCharacter, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<GameCharacter>(gameCharacter, HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/quest/level/{levelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameLevel> getLevelById(int levelId) {

        GameLevel gameLevel = questService.getGameLevelById(levelId);

        if(gameLevel != null){
            return new ResponseEntity<GameLevel>(gameLevel, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<GameLevel>(gameLevel, HttpStatus.NO_CONTENT);
        }
    }
}
