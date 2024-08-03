package org.player.resource;

import org.player.entity.PlayerPlant;
import org.player.entity.PlayerQuest;
import org.player.service.PlayerQuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerQuestResourceImpl implements PlayerQuestResource {

    @Autowired
    PlayerQuestService playerQuestService;

    @PostMapping(path = "/player-quests/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerQuest> savePlayerQuest(@RequestBody PlayerQuest playerQuest) {

        PlayerQuest newPq = playerQuestService.savePlayerQuest(playerQuest);

        if(newPq != null) {
            return new ResponseEntity<>(newPq, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/player-quests/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerQuest>> getPlayersQuests(@PathVariable int playerId) {

        List<PlayerQuest> playerQuests = playerQuestService.getPlayerQuestsByPlayerId(playerId);

        if(playerQuests != null) {
            return new ResponseEntity<List<PlayerQuest>>(playerQuests, HttpStatus.OK);
        } else{
            return new ResponseEntity<List<PlayerQuest>>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/player-quests/one/{playerId}/{questId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerQuest> getPlayerQuest(int playerId, int questId) {

        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(playerId, questId);

        if(playerQuest != null) {
            return new ResponseEntity<PlayerQuest>(playerQuest, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
