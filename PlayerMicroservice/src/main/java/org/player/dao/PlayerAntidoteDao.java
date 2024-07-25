package org.player.dao;

import org.player.entity.PlayerAntidote;
import org.player.entity.PlayerPlant;
import org.player.entity.key.PlayerAntidoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerAntidoteDao extends JpaRepository<PlayerAntidote, PlayerAntidoteKey> {

    @Query("SELECT pa FROM PlayerAntidote pa WHERE pa.playerAntidoteKey.playerId = :playerId")
    List<PlayerAntidote> findByPlayerId(@Param("playerId") int playerId);
}
