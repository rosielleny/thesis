package org.druid.controller;

import org.druid.entity.original.Antidote;
import org.druid.service.game.PlayerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AntidoteController {

    // Dummy player for development purposes
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    PlayerGameService playerGameService;

    @CrossOrigin
    @RequestMapping("/antidotes")
    public ModelAndView showAntidotesShelf(){

        ModelAndView mav = new ModelAndView("antidotes");

        List<Antidote> antidoteList = playerGameService.getPlayersAntidotes(DUMMY_PLAYER_ID);
        mav.addObject("antidoteList", antidoteList);

        return mav;
    }
}
