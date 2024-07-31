package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerAntidote;

import java.util.List;

public interface PlayerAntidoteServiceAgg {

    // Create and Update
    public PlayerAntidote savePlayerAntidote(PlayerAntidote playerAntidote);
    // Read
    public PlayerAntidote getPlayerAntidoteById(int playerId, int antidoteId);
    public List<PlayerAntidote> getPlayerAntidotesByPlayerId(int playerId);
}
