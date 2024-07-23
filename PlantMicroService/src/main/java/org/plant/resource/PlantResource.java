package org.plant.resource;

import org.plant.entity.CompendiumPage;
import org.plant.entity.Plant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PlantResource {

    ResponseEntity<Plant> getPlantById(@PathVariable int plantId);
    ResponseEntity<Plant> getPlantByName(@PathVariable String plantName);
    ResponseEntity<CompendiumPage> getCompendiumPageByPlantId(@PathVariable int plantId);
}
