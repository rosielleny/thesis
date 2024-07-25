package org.player.dao;

import org.player.entity.PlayerPlant;
import org.player.entity.PlayerQuest;
import org.player.entity.key.PlayerQuestKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerQuestDao  extends JpaRepository<PlayerQuest, PlayerQuestKey> {

    @Query("SELECT pq FROM PlayerQuest pq WHERE pq.playerQuestKey.playerId = :playerId")
    List<PlayerQuest> findByPlayerId(@Param("playerId") int playerId);

}
