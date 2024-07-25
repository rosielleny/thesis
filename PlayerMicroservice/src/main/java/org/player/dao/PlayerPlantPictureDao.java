package org.player.dao;

import org.player.entity.PlayerPlantPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPlantPictureDao extends JpaRepository<PlayerPlantPicture, Integer> {

   @Query("FROM PlayerPlantPicture WHERE playerId = :playerId AND plantId = :plantId")
   List<PlayerPlantPicture> getPlayerPlantPicturesForOnePlant(@Param("playerId") int playerId, @Param("plantId") int plantId);
}
