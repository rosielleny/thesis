package org.game.service;

import org.game.entity.ActionType;
import org.game.entity.Antidote;

import org.game.entity.PlantAntidote;

import java.util.List;

public interface AntidoteService {

    List<Antidote> getAllAntidotes();
    Antidote getAntidoteById(int id);
    Antidote getAntidoteByName(String name);
    List<PlantAntidote> getPlantAntidoteByAntidoteId(int antidoteId);
    List<ActionType> getActionTypeByAntidoteId(int antidoteId); // Uses AntidoteAction to return the action types for this antidote
}
