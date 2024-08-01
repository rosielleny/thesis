package org.druid.service.microserviceCom.player;

import org.druid.entity.original.Player;
import org.druid.entity.original.PlayerQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerServiceAggImpl implements PlayerServiceAgg {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7003/player/";


    @Override
    public Player savePlayer(Player player) {

        String url = startUrl + "save";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Player> requestEntity = new HttpEntity<>(player, httpHeaders);

        ResponseEntity<Player> responseEntity = restTemplate.postForEntity(url, requestEntity, Player.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    @Override
    public Player getPlayer(int playerId) {

        String url = startUrl + playerId;
        return restTemplate.getForObject(url, Player.class);
    }

    @Override
    public boolean deletePlayer(int playerId) {

        String url = startUrl + "delete/" + playerId;
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        return response.getStatusCode() == HttpStatus.OK;
    }
}
