package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerPlantPicture;

import java.util.List;

public interface PlayerPlantPictureServiceAgg {

    // Create and Update
    public PlayerPlantPicture savePlayerPlantPicture(PlayerPlantPicture playerPlantPicture);

    // Read
    public PlayerPlantPicture getPlayerPlantPictureById(int id);
    public List<PlayerPlantPicture> getPicturesByPlayerAndPlantId(int playerId, int plantId);
}
