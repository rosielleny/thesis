package org.player.resource;

import org.player.entity.Player;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

public interface PlayerResource {


    public ResponseEntity<Player> savePlayer(@RequestBody Player player);

    public ResponseEntity<Player> getPlayer(@PathVariable int playerId);

    public ResponseEntity<Boolean> deletePlayer(@PathVariable int playerId);

}
