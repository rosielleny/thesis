package org.druid.service.game;

import org.druid.entity.composite.AntidoteGame;
import org.druid.entity.original.*;
import org.druid.entity.original.key.PlantAntidoteKey;
import org.druid.service.microserviceCom.gameMechanics.AntidoteServiceAgg;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AntidoteGameServiceImpl implements AntidoteGameService {

    @Autowired
    AntidoteServiceAgg antidoteService;
    @Autowired
    PlantServiceAgg plantService;

    /* Brings together information from all antidote tables to provide the info
    * needed for one antidote crafting minigame*/
    @Override
    public AntidoteGame getAntidoteGame(int antidoteId) {

        Antidote antidote = antidoteService.getAntidoteById(antidoteId);
        AntidoteGame antidoteGame = new AntidoteGame();

        antidoteGame.setAntidoteGameFromAntidote(antidote);

        antidoteGame.setAntidotePlants(getAntidotePlantsList(antidoteId));

        antidoteGame.setAntidoteActions(antidoteService.getActionTypeByAntidoteId(antidoteId));

        return antidoteGame;
    }

    /* Called by getAntidoteGame to get a list of all the plants used in the antidote*/
    private List<Plant> getAntidotePlantsList(int antidoteId){

        List<PlantAntidote> plantidotes = antidoteService.getPlantAntidoteByAntidoteId(antidoteId);
        List<Plant> plants = new ArrayList<>();

        for(PlantAntidote pA : plantidotes){

            // Getting the key from this PlantAntidote so as to retrieve the plant ID
            PlantAntidoteKey key = pA.getPlantAntidoteKey();
            // Retrieving the plant using the ID in the key
            Plant plant = plantService.getPlantById(key.getPlantId());
            plants.add(plant); // Adding the plant to the list
        }

        return plants;
    }


}
