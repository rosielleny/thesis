package org.druid.service.microserviceCom.gameMechanics;



import org.druid.entity.original.ActionType;
import org.druid.entity.original.Antidote;
import org.druid.entity.original.PlantAntidote;

import java.util.List;

public interface AntidoteServiceAgg {

    List<Antidote> getAllAntidotes();
    Antidote getAntidoteById(int id);
    Antidote getAntidoteByName(String name);
    List<PlantAntidote> getPlantAntidoteByAntidoteId(int antidoteId);
    List<ActionType> getActionTypeByAntidoteId(int antidoteId); // Uses AntidoteAction to return the action types for this antidote
}
