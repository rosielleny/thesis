package org.player.service;

import org.player.dao.PlayerPlantDao;
import org.player.entity.PlayerPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerPlantServiceImpl implements PlayerPlantService {

    @Autowired
    PlayerPlantDao plantDao;

    public PlayerPlant savePlayerPlant(PlayerPlant plant) {
        return plantDao.save(plant);
    }

    public List<PlayerPlant> getPlayerPlants(int playerId) {
        return plantDao.findByPlayerId(playerId);
    }
}
