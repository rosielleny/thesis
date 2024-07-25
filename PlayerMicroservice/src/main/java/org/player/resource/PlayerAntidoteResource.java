package org.player.resource;

import org.player.entity.PlayerAntidote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlayerAntidoteResource {

    ResponseEntity<PlayerAntidote> savePlayerAntidote(@RequestBody PlayerAntidote playerAntidote);
    ResponseEntity<PlayerAntidote>  getPlayerAntidoteById(@PathVariable int playerId, @PathVariable int antidoteId);
    ResponseEntity<List<PlayerAntidote>>  getPlayerAntidotesByPlayerId(@PathVariable int playerId);

}
