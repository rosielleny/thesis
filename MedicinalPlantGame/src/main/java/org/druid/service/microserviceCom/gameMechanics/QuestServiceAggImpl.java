package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.GameCharacter;
import org.druid.entity.original.GameLevel;
import org.druid.entity.original.Player;
import org.druid.entity.original.Quest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
