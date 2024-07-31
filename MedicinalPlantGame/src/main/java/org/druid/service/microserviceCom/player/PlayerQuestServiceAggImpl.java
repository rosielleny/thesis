package org.druid.service.microserviceCom.player;


import org.druid.entity.original.PlayerQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerQuestServiceAggImpl implements PlayerQuestServiceAgg {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7003/player-quests/";

    // Create new playerQuest entry
    @Override
    public PlayerQuest savePlayerQuest(PlayerQuest playerQuest) {

        String url = startUrl + "save";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PlayerQuest> requestEntity = new HttpEntity<>(playerQuest, httpHeaders);

        ResponseEntity<PlayerQuest> responseEntity = restTemplate.postForEntity(url, requestEntity, PlayerQuest.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    // Getting all quests the player has unlocked
    @Override
    public List<PlayerQuest> getPlayerQuestsByPlayerId(int playerId) {

        String url = startUrl + playerId;

        ResponseEntity<PlayerQuest[]> response =  restTemplate.getForEntity(url, PlayerQuest[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }

    // Getting one playerQuest entry
    @Override
    public PlayerQuest getPlayerQuestById(int playerId, int questId) {

        String url = startUrl + "one/" + playerId + "/" + questId;
        return restTemplate.getForObject(url, PlayerQuest.class);
    }
}
