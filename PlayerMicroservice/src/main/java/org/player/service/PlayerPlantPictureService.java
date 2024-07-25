package org.player.service;

import org.player.entity.PlayerPlantPicture;

import java.util.List;

public interface PlayerPlantPictureService {

    // Create and Update
    public PlayerPlantPicture savePlayerPlantPicture(PlayerPlantPicture playerPlantPicture);

    // Read
    public PlayerPlantPicture getPlayerPlantPictureById(int id);
    public List<PlayerPlantPicture> getPicturesByPlayerAndPlantId(int playerId, int plantId);
}
