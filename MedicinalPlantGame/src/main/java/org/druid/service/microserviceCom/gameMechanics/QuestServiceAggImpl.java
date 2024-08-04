package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuestServiceAggImpl implements QuestServiceAgg {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7002/quest/";


    @Override
    public Quest getQuestByQuestId(int questId) {
        String url = startUrl + questId;
        return restTemplate.getForObject(url, Quest.class);
    }

    // Getting characters for quests
    @Override
    public GameCharacter getGameCharacterById(int gameCharacterId) {
        String url = startUrl + "character/" + gameCharacterId;
        return restTemplate.getForObject(url, GameCharacter.class);
    }

    // Used for checking how much xp is required for each level
    @Override
    public GameLevel getGameLevelById(int gameLevelId) {
        String url = startUrl + "level/" + gameLevelId;
        return restTemplate.getForObject(url, GameLevel.class);
    }

    @Override
    public List<Quest> getQuestsByGameLevelId(int gameLevelId) {
        String url = startUrl + "quest-by-level/" + gameLevelId;

        ResponseEntity<List<Quest>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Quest>>() {});

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        else{
            return null;
        }
    }
}
