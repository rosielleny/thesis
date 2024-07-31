package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerPlant;
import org.druid.entity.original.PlayerPlantPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerPlantServiceAggImpl implements PlayerPlantServiceAgg {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7003/player-plants/";

    // Creating a player plant (when a player has discovered a new plant)
    @Override
    public PlayerPlant savePlayerPlant(PlayerPlant plant) {
        String url = startUrl + "save";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PlayerPlant> requestEntity = new HttpEntity<>(plant, httpHeaders);

        ResponseEntity<PlayerPlant> responseEntity = restTemplate.postForEntity(url, requestEntity, PlayerPlant.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    // Getting all plants discovered by one player
    @Override
    public List<PlayerPlant> getPlayerPlants(int playerId) {

        String url = startUrl + playerId;

        ResponseEntity<PlayerPlant[]> response =  restTemplate.getForEntity(url, PlayerPlant[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }
}
