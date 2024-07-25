package org.player.service;

import org.player.entity.Player;

public interface PlayerService {

    // Create and Update
    public Player savePlayer(Player player);

    // Read
    public Player getPlayer(int id);

    // Delete
    public boolean deletePlayer(int playerId);
}
