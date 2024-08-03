package org.druid.controller;


import org.druid.entity.composite.CompendiumEntry;
import org.druid.entity.original.Plant;
import org.druid.service.game.CompendiumService;
import org.druid.service.game.PlayerGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CompendiumController {

    // Using dummy data to identify the player until sessions are implemented
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    CompendiumService compendiumService;
    @Autowired
    PlayerGameService playerGameService;

    @CrossOrigin
    @RequestMapping("/compendium")
    public ModelAndView showCompendiumContentsPage() {

        ModelAndView mav = new ModelAndView("compendium");

        // Getting all plants discovered by player for displaying the compendium contents page
        List<Plant> playersPlants = playerGameService.getPlayersPlants(DUMMY_PLAYER_ID);

        mav.addObject("plantList", playersPlants);

        return mav;
    }

    @CrossOrigin
    @RequestMapping(value = "/compendium/plant/{plantId}", method = RequestMethod.GET)
    public ModelAndView showCompendiumPage(@PathVariable("plantId") int plantId){

        ModelAndView mav = new ModelAndView("compendiumPage");

        CompendiumEntry page = compendiumService.getCompendiumEntry(plantId, DUMMY_PLAYER_ID);
        mav.addObject("page", page);
        return mav;
    }
}
