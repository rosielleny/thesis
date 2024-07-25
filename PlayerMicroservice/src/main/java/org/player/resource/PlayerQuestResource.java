package org.player.resource;

import org.player.entity.PlayerQuest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlayerQuestResource {


    public ResponseEntity<PlayerQuest> savePlayerQuest(@RequestBody PlayerQuest playerQuest);
    public ResponseEntity<List<PlayerQuest>> getPlayersQuests(@PathVariable int playerId);
    public ResponseEntity<PlayerQuest> getPlayerQuest(@PathVariable int playerId, @PathVariable int questId);

}
