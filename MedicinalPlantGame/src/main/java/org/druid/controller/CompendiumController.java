package org.druid.controller;

import jakarta.validation.Valid;
import org.druid.entity.composite.CompendiumEntry;
import org.druid.entity.original.Plant;
import org.druid.service.game.CompendiumService;
import org.druid.service.game.PlayerGameService;
import org.druid.service.microserviceCom.plant.PlantServiceAgg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/compendium/plant")
    public ModelAndView showCompendiumPage(@Valid @ModelAttribute("plant") Plant plant, BindingResult results){

        ModelAndView mav = new ModelAndView("compendiumPage");

        CompendiumEntry page = compendiumService.getCompendiumEntry(plant.getPlantId(), DUMMY_PLAYER_ID);
        mav.addObject("page", page);
        return mav;
    }
}
