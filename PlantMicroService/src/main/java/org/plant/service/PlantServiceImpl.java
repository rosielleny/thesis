package org.plant.service;

import org.plant.dao.CompendiumDao;
import org.plant.dao.PlantDao;
import org.plant.entity.CompendiumPage;
import org.plant.entity.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantServiceImpl implements PlantService{

    @Autowired
    private PlantDao plantDao;
    @Autowired
    private CompendiumDao compendiumDao;

    // Plants
    public List<Plant> getAllPlants() {
        return plantDao.findAll();
    }

    public Plant getPlantById(int id) {
        return plantDao.findById(id).orElse(null);
    }

    public Plant getPlantByName(String name) {
        return plantDao.findByName(name).orElse(null);
    }

    // Compendium

    public CompendiumPage getCompendiumPageByPlantId(int plantId) {
        return compendiumDao.findById(plantId).orElse(null);
    }
}
