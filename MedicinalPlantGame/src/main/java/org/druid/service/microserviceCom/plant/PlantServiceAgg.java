package org.druid.service.microserviceCom.plant;

import org.druid.entity.original.CompendiumPage;
import org.druid.entity.original.Plant;

import java.util.List;

public interface PlantServiceAgg {

    List<Plant> getAllPlants();
    Plant getPlantById(int id);
    Plant getPlantByName(String name);
    CompendiumPage getCompendiumPageByPlantId(int plantId);
}
