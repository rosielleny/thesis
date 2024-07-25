package org.player.resource;

import org.player.entity.PlayerPlant;
import org.player.entity.PlayerPlantPicture;
import org.player.service.PlayerPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerPlantResourceImpl implements PlayerPlantResource {

    @Autowired
    private PlayerPlantService plantService;

    @PostMapping(path = "/player-plants/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerPlant> savePlayerPlant(@RequestBody PlayerPlant playerPlant) {

        PlayerPlant newPp = plantService.savePlayerPlant(playerPlant);

        if(newPp != null) {
            return new ResponseEntity<>(newPp, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin
    @GetMapping(path = "/player-plant-picture/{playerId}/{plantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerPlant>> getPlayersPlants( @PathVariable int playerId) {

        List<PlayerPlant> playerPlants = plantService.getPlayerPlants(playerId);

        if(playerPlants != null) {
            return new ResponseEntity<List<PlayerPlant>>(playerPlants, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<List<PlayerPlant>>(HttpStatus.BAD_REQUEST);
        }
    }
}
