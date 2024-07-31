package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.ActionType;
import org.druid.entity.original.Antidote;
import org.druid.entity.original.PlantAntidote;
import org.druid.entity.original.PlayerQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AntidoteServiceAggImpl implements AntidoteServiceAgg {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7002/antidotes/";

    @Override
    public List<Antidote> getAllAntidotes() {

        String url = startUrl + "all";

        ResponseEntity<List<Antidote>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Antidote>>() {});

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        else{
            return null;
        }
    }

    @Override
    public Antidote getAntidoteById(int id) {

        String url = startUrl + id;
        return restTemplate.getForObject(url, Antidote.class);
    }

    @Override
    public Antidote getAntidoteByName(String name) {

        String url = startUrl + "name/" + name;
        return restTemplate.getForObject(url, Antidote.class);
    }

    // Getting the plants required to make an antidote
    @Override
    public List<PlantAntidote> getPlantAntidoteByAntidoteId(int antidoteId) {
        String url = startUrl + "plant-antidotes/" + antidoteId;

        ResponseEntity<PlantAntidote[]> response =  restTemplate.getForEntity(url, PlantAntidote[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }

    // Getting the actions required to make an antidote
    @Override
    public List<ActionType> getActionTypeByAntidoteId(int antidoteId) {

        String url = startUrl + "actions/" + antidoteId;

        ResponseEntity<ActionType[]> response =  restTemplate.getForEntity(url, ActionType[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            return Arrays.asList(response.getBody());
        } else{
            return null;
        }
    }
}
