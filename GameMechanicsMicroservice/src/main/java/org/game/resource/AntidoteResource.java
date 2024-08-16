package org.game.resource;

import org.game.entity.ActionType;
import org.game.entity.Antidote;
import org.game.entity.PlantAntidote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AntidoteResource {

    ResponseEntity<List<Antidote>> getAllAntidotes();
    ResponseEntity<Antidote> getAntidoteByName(@PathVariable String antidoteName);
    ResponseEntity<Antidote> getAntidoteId(@PathVariable int antidoteId);
    ResponseEntity<List<PlantAntidote>> getPlantAntidoteByAntidoteId(@PathVariable int antidoteId);
    ResponseEntity<List<ActionType>> getActionTypeByAntidoteId(@PathVariable int antidoteId);


}
