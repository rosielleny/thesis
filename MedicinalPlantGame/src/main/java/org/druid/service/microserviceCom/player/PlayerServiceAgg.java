package org.druid.service.microserviceCom.player;

import org.druid.entity.original.Player;

public interface PlayerServiceAgg {

    // Create and Update
    public Player savePlayer(Player player);

    // Read
    public Player getPlayer(int id);

    // Delete
    public boolean deletePlayer(int playerId);
}
