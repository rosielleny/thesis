package org.druid.controller;

import org.druid.entity.composite.CompendiumEntry;
import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.*;
import org.druid.entity.original.key.PlayerPlantKey;
import org.druid.service.game.CompendiumService;
import org.druid.service.game.PlayerGameService;
import org.druid.service.game.QuestGameService;
import org.druid.service.game.QuestGameServiceImpl;
import org.druid.service.microserviceCom.gameMechanics.AntidoteServiceAgg;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.druid.service.microserviceCom.player.PlayerQuestServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestController {

    // Dummy player for development purposes
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    private PlayerGameService playerGameService;
    @Autowired
    private PlayerQuestServiceAgg playerQuestService;
    @Autowired
    private QuestGameService questGameService;
    @Autowired
    private PlantServiceAgg plantService;
    @Autowired
    private CompendiumService compendiumService;
    @Autowired
    private AntidoteServiceAgg antidoteService;

    @CrossOrigin
    @RequestMapping("/quests")
    public ModelAndView showQuests(){

        ModelAndView mav = new ModelAndView("quests");

        List<QuestGame> questList = playerGameService.getPlayersQuests(DUMMY_PLAYER_ID);

        if (questList == null) {
            questList = new ArrayList<>();
        }

        List<QuestGame> activeQuest = new ArrayList<>();
        List<QuestGame> inactiveQuests = new ArrayList<>();
        List<QuestGame> completeQuests = new ArrayList<>();

        for(QuestGame qg : questList){

            if(qg.getPlayerQuestStatus().equals("Active")){
                activeQuest.add(qg);
            }else if(qg.getPlayerQuestStatus().equals("Inactive")){
                inactiveQuests.add(qg);
            }
            else{
                completeQuests.add(qg);
            }
        }

        mav.addObject("activeQuest", activeQuest);
        mav.addObject("inactiveQuests", inactiveQuests);
        mav.addObject("completedQuests", completeQuests);
        return mav;
    }

    @CrossOrigin
    @RequestMapping("/quests/{questId}")
    public ModelAndView showQuestInfo(@PathVariable int questId){

        ModelAndView mav = new ModelAndView("questsInfo");

        List<QuestGame> questList = playerGameService.getPlayersQuests(DUMMY_PLAYER_ID);
        QuestGame quest = new QuestGame();
        for(QuestGame qg : questList){
            if(qg.getQuestId() == questId){
                quest = qg;
            }
        }

        String fromCompleter = "no";
        // Attributes to flag what to display
        mav.addObject("fromCompleter", fromCompleter);

        mav.addObject("quest", quest);
        return mav;
    }

    @CrossOrigin
    @RequestMapping("/quests/mark-active")
    public ModelAndView markQuestAsActive(@RequestParam("questId") int questId){

        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(DUMMY_PLAYER_ID, questId);
        List<PlayerQuest> playerQuests = playerQuestService.getPlayerQuestsByPlayerId(DUMMY_PLAYER_ID);
        // Whichever quest was previously active must now be inactive
        for(PlayerQuest pq : playerQuests){
            if(pq.getQuestStatus().equals("Active")){
                pq.setQuestStatus("Inactive");
                playerQuestService.savePlayerQuest(pq);
                break;
            }
        }
        playerQuest.setQuestStatus("Active");
        playerQuestService.savePlayerQuest(playerQuest);

        return new ModelAndView("redirect:/quests");
    }

    @CrossOrigin
    @RequestMapping("/quests/mark-complete")
    public ModelAndView markQuestAsComplete(@RequestParam("questId") int questId){

        ModelAndView mav = new ModelAndView("questsInfo");

        boolean completed = questGameService.completedQuest(questId, DUMMY_PLAYER_ID);
        PlayerQuest playerQuest = playerQuestService.getPlayerQuestById(DUMMY_PLAYER_ID, questId);
        List<QuestGame> questList = playerGameService.getPlayersQuests(DUMMY_PLAYER_ID);
        QuestGame quest = new QuestGame();
        for(QuestGame qg : questList){
            if(qg.getQuestId() == questId){
                quest = qg;
            }
        }


        String fromCompleter = "fromCompleter";
        // Attributes to flag what to display
        mav.addObject("fromCompleter", fromCompleter);
        // The quest
        mav.addObject("quest", quest);

        return mav;
    }

    @CrossOrigin
    @RequestMapping("/map")
    public ModelAndView showMap(){

        ModelAndView mav = new ModelAndView("map");

        List<PlayerQuest> playerQuests = playerQuestService.getPlayerQuestsByPlayerId(DUMMY_PLAYER_ID);
        QuestGame quest = new QuestGame();
        Plant plant = new Plant();
        for(PlayerQuest pq : playerQuests){
            if(pq.getQuestStatus().equals("Active") && pq.getQuestStage().equals("Beginning")){
                quest = questGameService.getQuestGame(pq.getPlayerQuestKey().getQuestId(), DUMMY_PLAYER_ID);
                plant = plantService.getPlantById(quest.getPlantId());
                break;
            }
            else{
                quest.setQuestName("no-quest");
                plant.setPlantName("no-plant");
            }
        }
        mav.addObject("quest", quest);
        mav.addObject("plant", plant);
        return mav;


    }


@CrossOrigin
@RequestMapping("/find-plant/{questId}")
public ModelAndView findPlant(@PathVariable("questId") int questId){

    ModelAndView mav = new ModelAndView("findPlant");

    QuestGame quest = questGameService.getQuestGame(questId, DUMMY_PLAYER_ID);
    Plant plant = plantService.getPlantById(quest.getPlantId());
    mav.addObject("plant", plant);
    mav.addObject("quest", quest);
    return mav;

}


@CrossOrigin
@RequestMapping("/found-plant/{plantId}/{questId}")
public ModelAndView foundPlant(@PathVariable("questId") int questId, @PathVariable("plantId") int plantId){

    ModelAndView mav = new ModelAndView("foundPlant");

    QuestGame quest = questGameService.getQuestGame(questId, DUMMY_PLAYER_ID);
    Plant plant = plantService.getPlantById(quest.getPlantId());
    mav.addObject("plant", plant);
    mav.addObject("quest", quest);
    return mav;

    }

/*This is the point in the game play loop where image recognition should be implemented
* to verify the player has found the correct plant*/
@CrossOrigin
@RequestMapping("/confirm-plant/{plantId}/{questId}")
public ModelAndView confirmPlant(@PathVariable("questId") int questId, @PathVariable("plantId") int plantId){

    ModelAndView mav = new ModelAndView("confirmPlant");

    QuestGame quest = questGameService.getQuestGame(questId, DUMMY_PLAYER_ID);
    Plant plant = plantService.getPlantById(quest.getPlantId());
    mav.addObject("plant", plant);
    mav.addObject("quest", quest);
    return mav;

}

    @CrossOrigin
    @RequestMapping("/plant-facts/{questId}/{plantId}/{factIndex}")
    public ModelAndView displayPlantFacts(@PathVariable("questId") int questId, @PathVariable("plantId") int plantId, @PathVariable("factIndex")Byte factIndex){

        ModelAndView mav = new ModelAndView();
        byte maxFact = 0;
        CompendiumEntry plant = compendiumService.getCompendiumEntry(plantId, DUMMY_PLAYER_ID);
        QuestGame quest = questGameService.getQuestGame(questId, DUMMY_PLAYER_ID);
        // This for loop counts how many unique features have been filled out for the plant, and thus how many times they need to be displayed
        for(String fact: plant.getUniqueFeatures()){
            if(!fact.isEmpty()){
                maxFact ++;
            }
        }
        if(maxFact > factIndex) {
            factIndex++;
            mav.setViewName("plantFacts");
            mav.addObject("plant", plant);
            mav.addObject("quest", quest);
            mav.addObject("factIndex", factIndex);
            return mav;
        }
        else{

            playerGameService.addNewPlayerPlantAndAntidote(plantId, DUMMY_PLAYER_ID, questId);
            playerGameService.updateQuestStage(questId, DUMMY_PLAYER_ID, "PlantFound");
            String antidoteName = antidoteService.getAntidoteById(quest.getAntidoteId()).getAntidoteName();
            mav.setViewName("plantSuccess");
            mav.addObject("antidoteName", antidoteName);
            mav.addObject("plant", plant);
            mav.addObject("quest", quest);
            return mav;
        }

    }

}
