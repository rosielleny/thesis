package org.player.service;

import org.player.dao.PlayerDao;
import org.player.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public Player savePlayer(Player player) {
        return playerDao.save(player);
    }

    public Player getPlayer(int id) {
        return playerDao.findById(id).orElse(null);
    }

    public boolean deletePlayer(int playerId) {

        playerDao.deleteById(playerId);
        return true;
    }
}
