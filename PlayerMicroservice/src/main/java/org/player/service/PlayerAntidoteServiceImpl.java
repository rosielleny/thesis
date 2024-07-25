package org.player.service;

import org.player.dao.PlayerAntidoteDao;
import org.player.entity.PlayerAntidote;
import org.player.entity.key.PlayerAntidoteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerAntidoteServiceImpl implements PlayerAntidoteService {

    @Autowired
    private PlayerAntidoteDao playerAntidoteDao;

    @Override
    public PlayerAntidote savePlayerAntidote(PlayerAntidote playerAntidote) {
        return playerAntidoteDao.save(playerAntidote);
    }

    public PlayerAntidote getPlayerAntidoteById(int playerId, int antidoteId) {
        PlayerAntidoteKey key = new PlayerAntidoteKey();
        key.setAntidoteId(antidoteId);
        key.setPlayerId(playerId);
        return playerAntidoteDao.findById(key).orElse(null);
    }


    public List<PlayerAntidote> getPlayerAntidotesByPlayerId(int playerId) {
        return playerAntidoteDao.findByPlayerId(playerId);
    }


}
