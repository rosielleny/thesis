package org.player.service;

import org.player.entity.PlayerAntidote;

import java.util.List;

public interface PlayerAntidoteService {

    // Create and Update
    public PlayerAntidote savePlayerAntidote(PlayerAntidote playerAntidote);
    // Read
    public PlayerAntidote getPlayerAntidoteById(int playerId, int antidoteId);
    public List<PlayerAntidote> getPlayerAntidotesByPlayerId(int playerId);

}
