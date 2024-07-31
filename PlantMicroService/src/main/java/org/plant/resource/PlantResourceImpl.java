package org.plant.resource;

import org.plant.entity.CompendiumPage;
import org.plant.entity.Plant;
import org.plant.service.PlantService;
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
public class PlantResourceImpl implements PlantResource {

    @Autowired
    private PlantService plantService;

    //
    @CrossOrigin
    @GetMapping(path = "/plants/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Plant>> getAllPlants() {

        List<Plant> plants = plantService.getAllPlants();

        if(plants != null){
            return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Plant>>(plants, HttpStatus.NO_CONTENT);
        }
    }

    // Get plant by plant ID
    @CrossOrigin
    @GetMapping(path = "/plants/{plantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plant> getPlantById(@PathVariable int plantId) {

        Plant plant = plantService.getPlantById(plantId);

        if(plant != null){
            return new ResponseEntity<Plant>(plant, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Plant>(plant, HttpStatus.NO_CONTENT);
        }

    }

    // Get plant by plant name
    @CrossOrigin
    @GetMapping(path = "/plants/name/{plantName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plant> getPlantByName(@PathVariable String plantName) {

        Plant plant = plantService.getPlantByName(plantName);

        if(plant != null){
            return new ResponseEntity<Plant>(plant, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Plant>(plant, HttpStatus.NO_CONTENT);
        }
    }

    // Get compendium page by plant ID
    @CrossOrigin
    @GetMapping(path = "/plants/compendium/{plantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompendiumPage> getCompendiumPageByPlantId(@PathVariable int plantId) {

        CompendiumPage page = plantService.getCompendiumPageByPlantId(plantId);

        if(page != null){
            return new ResponseEntity<CompendiumPage>(page, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<CompendiumPage>(page, HttpStatus.NO_CONTENT);
        }
    }
}
