package org.player.resource;

import org.player.entity.PlayerAntidote;
import org.player.service.PlayerAntidoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerAntidoteResourceImpl implements PlayerAntidoteResource {

    @Autowired
    PlayerAntidoteService playerAntidoteService;
    // Create/Update
    @PostMapping(path = "/player-antidote/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerAntidote> savePlayerAntidote(@RequestBody PlayerAntidote playerAntidote) {

        PlayerAntidote newPa = playerAntidoteService.savePlayerAntidote(playerAntidote);

        if(newPa != null) {
            return new ResponseEntity<>(newPa, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Read
    @CrossOrigin
    @GetMapping(path = "/player-antidote/{playerId}/{antidoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerAntidote> getPlayerAntidoteById(@PathVariable int playerId, @PathVariable int antidoteId) {

        PlayerAntidote pa = playerAntidoteService.getPlayerAntidoteById(playerId, antidoteId);

        if(pa != null) {
            return new ResponseEntity<PlayerAntidote>(pa, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<PlayerAntidote>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting a list of which plants a specific player has unlocked
    @CrossOrigin
    @GetMapping(path = "/player-antidote/player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerAntidote>> getPlayerAntidotesByPlayerId(@PathVariable int playerId) {

        List<PlayerAntidote> pas = playerAntidoteService.getPlayerAntidotesByPlayerId(playerId);

        if(pas != null) {
            return new ResponseEntity<List<PlayerAntidote>>(pas, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<PlayerAntidote>>(HttpStatus.NOT_FOUND);
        }
    }
}
