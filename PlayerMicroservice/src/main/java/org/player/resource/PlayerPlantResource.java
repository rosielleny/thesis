package org.player.resource;

import org.player.entity.PlayerPlant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlayerPlantResource {

    public ResponseEntity<PlayerPlant> savePlayerPlant(@RequestBody PlayerPlant playerPlant);
    public ResponseEntity<List<PlayerPlant>> getPlayersPlants(@PathVariable int playerId);
}
