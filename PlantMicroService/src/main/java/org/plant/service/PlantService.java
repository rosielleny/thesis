package org.plant.service;

import org.plant.entity.Plant;
import java.util.List;

public interface PlantService{

    List<Plant> getAllPlants();
    Plant getPlantById(int id);
    Plant getPlantByName(String name);
}