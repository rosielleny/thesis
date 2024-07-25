package org.player.service;

import org.player.dao.PlayerPlantPictureDao;
import org.player.entity.PlayerPlantPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerPlantPictureServiceImpl implements PlayerPlantPictureService {

    @Autowired
    PlayerPlantPictureDao plantPictureDao;

    public PlayerPlantPicture savePlayerPlantPicture(PlayerPlantPicture playerPlantPicture) {
        return plantPictureDao.save(playerPlantPicture);
    }

    public PlayerPlantPicture getPlayerPlantPictureById(int id) {
        return plantPictureDao.findById(id).orElse(null);
    }

    public List<PlayerPlantPicture> getPicturesByPlayerAndPlantId(int playerId, int plantId) {
        return plantPictureDao.getPlayerPlantPicturesForOnePlant(playerId, plantId);
    }
}
