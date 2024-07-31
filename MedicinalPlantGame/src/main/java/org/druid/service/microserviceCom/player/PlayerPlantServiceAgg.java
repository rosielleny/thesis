package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerPlant;

import java.util.List;

public interface PlayerPlantServiceAgg {

    // Create and update
    public PlayerPlant savePlayerPlant(PlayerPlant plant);
    // Read
    public List<PlayerPlant> getPlayerPlants(int playerId);
}
