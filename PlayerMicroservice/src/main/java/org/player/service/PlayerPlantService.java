package org.player.service;

import org.player.entity.PlayerPlant;

import java.util.List;

public interface PlayerPlantService {

    // Create and update
    public PlayerPlant savePlayerPlant(PlayerPlant plant);
    // Read
    public List<PlayerPlant> getPlayerPlants(int playerId);

}
