package org.druid.controller;

import org.druid.entity.composite.QuestGame;
import org.druid.entity.original.Antidote;
import org.druid.entity.original.PlayerQuest;
import org.druid.entity.original.Quest;
import org.druid.service.game.PlayerGameService;
import org.druid.service.game.QuestGameService;
import org.druid.service.game.QuestGameServiceImpl;
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
    PlayerGameService playerGameService;
    @Autowired
    PlayerQuestServiceAgg playerQuestService;
    @Autowired
    private QuestGameService questGameService;
    @Autowired
    private QuestGameServiceImpl questGameServiceImpl;

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
        playerQuest.setQuestStatus("Active");
        playerQuestService.savePlayerQuest(playerQuest);

        return new ModelAndView("redirect:/quests");
    }

    @CrossOrigin
    @RequestMapping("/quests/mark-complete")
    public ModelAndView markQuestAsComplete(@RequestParam("questId") int questId){

        ModelAndView mav = new ModelAndView("redirect:/questsInfo");

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
        for(PlayerQuest pq : playerQuests){
            if(pq.getQuestStatus().equals("Active") && pq.getQuestStage().equals("Beginning")){
                quest = questGameService.getQuestGame(pq.getPlayerQuestKey().getQuestId(), DUMMY_PLAYER_ID);
            }
            else{
                quest.setQuestName("no-quest");
            }
        }
        mav.addObject("quest", quest);
        return mav;
    }
}
