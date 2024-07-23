package org.game.resource;

import org.game.entity.ActionType;
import org.game.entity.Antidote;
import org.game.entity.PlantAntidote;
import org.game.service.AntidoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AntidoteResourceImpl implements AntidoteResource {

    @Autowired
    private AntidoteService antidoteService;

    // Get all
    @CrossOrigin
    @GetMapping(path = "/antidotes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Antidote>> getAllAntidotes() {
        List<Antidote> antidotes = antidoteService.getAllAntidotes();

        if(antidotes != null){
            return new ResponseEntity<List<Antidote>>(antidotes, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Antidote>>(antidotes, HttpStatus.NO_CONTENT);
        }
    }

    // Get antidote by name
    @CrossOrigin
    @GetMapping(path = "/antidotes/name/{antidoteName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Antidote> getAntidoteByName(@PathVariable String antidoteName) {

        Antidote antidote = antidoteService.getAntidoteByName(antidoteName);

        if(antidote != null){
            return new ResponseEntity<Antidote>(antidote, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Antidote>(antidote, HttpStatus.NO_CONTENT);
        }
    }

    // Get antidote by ID
    @CrossOrigin
    @GetMapping(path = "/antidotes/{antidoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Antidote> getAntidoteId(@PathVariable int antidoteId) {

        Antidote antidote = antidoteService.getAntidoteById(antidoteId);

        if(antidote != null){
            return new ResponseEntity<Antidote>(antidote, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Antidote>(antidote, HttpStatus.NO_CONTENT);
        }
    }

    // Getting all plant/antidote combinations
    @CrossOrigin
    @GetMapping(path = "/antidotes/plant-antidotes/{antidoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlantAntidote>> getPlantAntidoteByAntidoteId(@PathVariable int antidoteId) {

        List<PlantAntidote> plantidotes = antidoteService.getPlantAntidoteByAntidoteId(antidoteId);

        if(plantidotes != null){
            return new ResponseEntity<List<PlantAntidote>>(plantidotes, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<PlantAntidote>>(plantidotes, HttpStatus.NO_CONTENT);
        }
    }

    // Getting actions for one antidote game
    @CrossOrigin
    @GetMapping(path = "/antidotes/actions/{antidoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActionType>> getActionTypeByAntidoteId(@PathVariable int antidoteId) {

        List<ActionType> actions = antidoteService.getActionTypeByAntidoteId(antidoteId);

        if(actions != null){
            return new ResponseEntity<List<ActionType>>(actions, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<ActionType>>(actions, HttpStatus.NO_CONTENT);
        }
    }
}
