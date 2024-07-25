package org.player.dao;

import org.player.entity.PlayerPlant;
import org.player.entity.PlayerPlantPicture;
import org.player.entity.key.PlayerPlantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPlantDao extends JpaRepository<PlayerPlant, PlayerPlantKey> {

    @Query("SELECT pa FROM PlayerPlant pa WHERE pa.playerPlantKey.playerId = :playerId")
    List<PlayerPlant> findByPlayerId(@Param("playerId") int playerId);
}
