package org.game.dao;

import org.game.entity.GameCharacter;
import org.game.entity.GameLevel;
import org.game.entity.Quest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/* This DAO accesses data for Quest, GameCharacter, and GameLevel*/
@Repository
public interface QuestDao extends JpaRepository<Quest, Integer>{

    @Query("FROM GameCharacter WHERE gameCharacterId = :gameCharacterId")
    Optional<GameCharacter> findByGameCharacterId(@Param("gameCharacterId")int gameCharacterId);

    @Query("FROM GameLevel WHERE gameLevelId = :gameLevelId")
    Optional<GameLevel> findByGameLevelId(@Param("gameLevelId")int gameLevelId);

    @Query("FROM Quest WHERE requiredLevel = :playerLevel")
    List<Quest> findQuestByRequiredLevel(@Param("playerLevel")int playerLevel);

}
