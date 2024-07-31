package org.druid.service.microserviceCom.player;

import org.druid.entity.original.PlayerAntidote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerAntidoteServiceAggImpl implements PlayerAntidoteServiceAgg{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7003/player-antidote/";

    @Override
    public PlayerAntidote savePlayerAntidote(PlayerAntidote playerAntidote) {

        String url = startUrl + "save";
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlayerAntidote> requestEntity = new HttpEntity<>(playerAntidote, httpHeaders);

        ResponseEntity<PlayerAntidote> responseEntity = restTemplate.postForEntity(url, requestEntity, PlayerAntidote.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        else{
            return null;
        }
    }

    @Override
    public PlayerAntidote getPlayerAntidoteById(int playerId, int antidoteId) {

        String url = startUrl + playerId + "/" + antidoteId;
        return restTemplate.getForObject(url, PlayerAntidote.class);
    }

    @Override
    public List<PlayerAntidote> getPlayerAntidotesByPlayerId(int playerId) {

        String url = startUrl + "player/"+ playerId;

        ResponseEntity<PlayerAntidote[]> response =  restTemplate.getForEntity(url, PlayerAntidote[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }
}
