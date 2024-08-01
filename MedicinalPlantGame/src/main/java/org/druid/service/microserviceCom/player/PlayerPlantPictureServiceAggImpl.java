package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerAntidote;
import org.druid.entity.original.PlayerPlantPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerPlantPictureServiceAggImpl implements PlayerPlantPictureServiceAgg{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7003/player-plant-picture/";

    @Override
    public PlayerPlantPicture savePlayerPlantPicture(PlayerPlantPicture playerPlantPicture) {
        String url = startUrl + "save";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PlayerPlantPicture> requestEntity = new HttpEntity<>(playerPlantPicture, httpHeaders);

        ResponseEntity<PlayerPlantPicture> responseEntity = restTemplate.postForEntity(url, requestEntity, PlayerPlantPicture.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }else{
            return null;
        }

    }

    @Override
    public PlayerPlantPicture getPlayerPlantPictureById(int pictureId) {

        String url = startUrl + "one/" + pictureId;
        return restTemplate.getForObject(url, PlayerPlantPicture.class);
    }

    @Override
    public List<PlayerPlantPicture> getPicturesByPlayerAndPlantId(int playerId, int plantId) {

        String url = startUrl + playerId + "/" + plantId;

        ResponseEntity<PlayerPlantPicture[]> response =  restTemplate.getForEntity(url, PlayerPlantPicture[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }
}
