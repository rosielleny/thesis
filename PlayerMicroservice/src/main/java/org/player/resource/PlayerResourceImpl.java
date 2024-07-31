package org.player.resource;

import org.player.entity.Player;
import org.player.entity.PlayerQuest;
import org.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerResourceImpl implements PlayerResource {

    @Autowired
    private PlayerService playerService;

    @PostMapping(path = "/player/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> savePlayer(Player player) {

        Player newPlayer = playerService.savePlayer(player);

        if(newPlayer != null) {
            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayer(int playerId) {

        Player player = playerService.getPlayer(playerId);

        if(player != null){
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Player>(player, HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @DeleteMapping(path = "/player/delete/{playerId}")
    public ResponseEntity<Boolean> deletePlayer(int playerId) {

        boolean deleted = playerService.deletePlayer(playerId);

        if(deleted){
            return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Boolean>(deleted, HttpStatus.NO_CONTENT);
        }
    }
}
