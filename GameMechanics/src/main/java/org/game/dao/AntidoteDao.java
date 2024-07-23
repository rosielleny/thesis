package org.game.dao;

import org.game.entity.ActionType;
import org.game.entity.Antidote;
import org.game.entity.AntidoteAction;
import org.game.entity.PlantAntidote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AntidoteDao extends JpaRepository<Antidote, Integer> {

    @Query("FROM Antidote WHERE antidoteName = :actidoteName")
    Optional<Antidote> findAntidoteByName(String name);

    // Gets the ID for all actions associated with an antidote game
    @Query("FROM AntidoteAction WHERE antidoteId = :antidoteId")
    Optional<List<AntidoteAction>> findAntidoteActionByAntidoteId(int antidoteId);

    @Query("FROM ActionType WHERE actionId = :actionId")
    Optional<ActionType> findActionTypeByActionId(int actionId);

    @Query("FROM PlantAntidote WHERE antidoteId = :antidoteId")
    Optional<List<PlantAntidote>> findPlantAntidoteByAntidoteId(int antidoteId);
}
