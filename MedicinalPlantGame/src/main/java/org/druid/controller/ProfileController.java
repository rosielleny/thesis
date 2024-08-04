package org.druid.controller;

import jakarta.validation.Valid;
import org.druid.entity.composite.PlayerProfile;
import org.druid.entity.original.Player;
import org.druid.service.game.PlayerGameService;
import org.druid.service.microserviceCom.player.PlayerServiceAgg;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    // Dummy player for development purposes
    private static final int DUMMY_PLAYER_ID = 1;

    @Autowired
    PlayerGameService playerGameService;
    @Autowired
    PlayerServiceAgg playerService;

    @CrossOrigin
    @RequestMapping("/profile")
    public ModelAndView showProfilePage(){

        ModelAndView mav = new ModelAndView("profile");

        PlayerProfile profile = playerGameService.getPlayerProfile(DUMMY_PLAYER_ID);
        mav.addObject("profile", profile);

        return mav;
    }

    @CrossOrigin
    @RequestMapping("/profile/edit/{playerId}")
    public ModelAndView updateProfileInfo(@PathVariable int playerId){

        ModelAndView mav = new ModelAndView("profileUpdate");
        Player player = playerService.getPlayer(playerId);
        mav.addObject("player", player);
        return mav;
    }

    @CrossOrigin
    @RequestMapping("/profile/edit/saved")
    public ModelAndView saveNewProfileInfo(@Valid @ModelAttribute("player")Player player, BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            return new ModelAndView("profileUpdate", "player", player);
        }

        if(player != null){
            Player finalPlayer = playerService.getPlayer(DUMMY_PLAYER_ID);
            // Applying edited values to the original player object
            finalPlayer.setPlayerName(player.getPlayerName());
            finalPlayer.setPlayerEmail(player.getPlayerEmail());
            finalPlayer.setPlayerPhone(player.getPlayerPhone());
            // Saving the changes
            playerService.savePlayer(finalPlayer);
            PlayerProfile profile = playerGameService.getPlayerProfile(DUMMY_PLAYER_ID);
        }

        return new ModelAndView("redirect:/profile");
    }
}
