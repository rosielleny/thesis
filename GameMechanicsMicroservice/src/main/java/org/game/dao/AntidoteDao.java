package org.game.dao;

import org.game.entity.ActionType;
import org.game.entity.Antidote;
import org.game.entity.AntidoteAction;
import org.game.entity.PlantAntidote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* This DAO accesses data for Antidote, AntidoteAction, ActionType, and PlantAntidote*/
@Repository
public interface AntidoteDao extends JpaRepository<Antidote, Integer> {

    @Query("FROM Antidote WHERE antidoteName = :antidoteName")
    Optional<Antidote> findAntidoteByName(@Param("antidoteName") String name);

    // Gets the ID for all actions associated with an antidote game
    @Query("FROM AntidoteAction aa WHERE aa.antidoteActionKey.antidoteId = :antidoteId")
    List<AntidoteAction> findAntidoteActionsByAntidoteId(@Param("antidoteId") int antidoteId);

    @Query("FROM ActionType WHERE actionId = :actionId")
    Optional<ActionType> findActionTypeByActionId(@Param("actionId")int actionId);

    @Query("FROM PlantAntidote pa WHERE pa.plantAntidoteKey.antidoteId = :antidoteId")
    Optional<List<PlantAntidote>> findPlantAntidoteByAntidoteId(@Param("antidoteId")int antidoteId);

}
