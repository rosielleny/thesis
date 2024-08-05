package org.druid.controller;

import org.druid.entity.composite.AntidoteGame;
import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.Antidote;
import org.druid.entity.original.PlantAntidote;
import org.druid.entity.original.PlayerAntidote;
import org.druid.entity.original.PlayerQuest;
import org.druid.service.game.AntidoteGameService;
import org.druid.service.game.PlayerGameService;
import org.druid.service.game.QuestGameService;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerAntidoteServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.druid.service.microserviceCom.gameMechanics.AntidoteServiceAgg;
import java.util.List;

@Controller
public class AntidoteController {

    // Dummy player for development purposes
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    PlayerGameService playerGameService;
    @Autowired
    AntidoteGameService antidoteGameService;
    @Autowired
    QuestGameService questGameService;
    @Autowired
    AntidoteServiceAgg antidoteService;
    @Autowired
    PlantServiceAgg plantService;
    @Autowired
    PlayerAntidoteServiceAgg playerAntidoteService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;

    @CrossOrigin
    @RequestMapping("/antidotes")
    public ModelAndView showAntidotesShelf(){

        ModelAndView mav = new ModelAndView("antidotes");

        List<Antidote> antidoteList = playerGameService.getPlayersAntidotes(DUMMY_PLAYER_ID);
        mav.addObject("antidoteList", antidoteList);

        return mav;
    }

    @CrossOrigin
    @RequestMapping("/antidotes/{antidoteId}")
    public ModelAndView showOneAntidote(@PathVariable int antidoteId){

        ModelAndView mav = new ModelAndView("antidotesViewOne");

        AntidoteGame antidote = antidoteGameService.getAntidoteGame(antidoteId);
        List<QuestGame> playersQuests = playerGameService.getPlayersQuests(DUMMY_PLAYER_ID);

        boolean isQuest = false;
        for(QuestGame quest : playersQuests){
            if(!quest.getPlayerQuestStatus().equals("Complete")){
                if(quest.getAntidoteId() == antidoteId){
                    isQuest = true;
                    break;
                }
            }
        }
        mav.addObject("isQuest", isQuest);
        mav.addObject("antidote", antidote);

        return mav;
    }

    @CrossOrigin
    @RequestMapping("/antidotes/craft/{isQuest}/{antidoteId}")
    public ModelAndView craftAntidote(@PathVariable int antidoteId, @PathVariable boolean isQuest){

        ModelAndView mav = new ModelAndView("antidoteCraft");

        // Getting the name of the associated plant
        AntidoteGame antidote = antidoteGameService.getAntidoteGame(antidoteId);
        PlantAntidote plantA = antidoteService.getPlantAntidoteByAntidoteId(antidoteId).get(0);
        int plantId = plantA.getPlantAntidoteKey().getPlantId();
        String plantName = plantService.getPlantById(plantId).getPlantName();

        mav.addObject("isQuest", isQuest);
        mav.addObject("antidote", antidote);
        mav.addObject("plantName", plantName);
        return mav;
    }

    @CrossOrigin
    @RequestMapping("/antidotes/success/{isQuest}/{antidoteId}")
    public ModelAndView antidoteSuccess(@PathVariable int antidoteId, @PathVariable boolean isQuest){

        ModelAndView mav = new ModelAndView("antidoteSuccess");

        PlayerAntidote playerAntidote = playerAntidoteService.getPlayerAntidoteById(DUMMY_PLAYER_ID, antidoteId);
        playerAntidote.setNumberMade(playerAntidote.getNumberMade() +1);
        playerAntidoteService.savePlayerAntidote(playerAntidote);
        // Getting the name of the associated plant
        AntidoteGame antidote = antidoteGameService.getAntidoteGame(antidoteId);

        List<QuestGame> playerQuests = playerGameService.getPlayersQuests(DUMMY_PLAYER_ID);
        int questId = 0;
        for(QuestGame qg : playerQuests){
            if(qg.getAntidoteId() == antidoteId){
                questId = qg.getQuestId();
            }
        }

        // Updating the quest status
        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(DUMMY_PLAYER_ID, questId);
        playerQuest.setQuestStage("AntidoteMade");
        playerQuestService.savePlayerQuest(playerQuest);

        mav.addObject("isQuest", isQuest);
        mav.addObject("antidote", antidote);

        return mav;
    }
}
