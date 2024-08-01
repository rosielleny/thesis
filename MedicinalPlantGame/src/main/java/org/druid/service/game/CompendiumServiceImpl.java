package org.druid.service.game;

import org.druid.service.microserviceCom.player.PlayerPlantPictureServiceAgg;
import org.druid.entity.original.Plant;
import org.druid.entity.composite.CompendiumEntry;
import org.druid.entity.original.CompendiumPage;
import org.druid.entity.original.PlayerPlantPicture;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompendiumServiceImpl implements CompendiumService {

    @Autowired
    PlantServiceAgg plantService;
    @Autowired
    PlayerPlantPictureServiceAgg pPPService;

    @Override
    public CompendiumEntry getCompendiumEntry(int plantId, int playerId) {

        CompendiumPage page = plantService.getCompendiumPageByPlantId(plantId);
        Plant plant = plantService.getPlantById(plantId);

        CompendiumEntry entry = new CompendiumEntry();

        entry.setCompendiumEntryWithCompendiumPageAndPlant(page, plant);

        entry.setPlayerPictures(getPlayerPicturesList(plantId, playerId));

        return entry;
    }

    /* Called by getCompendiumEntry to get a list of the player's pictures of the given plant*/
    private List<String> getPlayerPicturesList(int plantId, int playerId){

        List<PlayerPlantPicture> pictures = pPPService.getPicturesByPlayerAndPlantId(playerId, plantId);
        List<String> playerPictures = new ArrayList<>();

        for(PlayerPlantPicture picture : pictures){
            playerPictures.add(picture.getPicture());
        }
        return playerPictures;
    }
}
