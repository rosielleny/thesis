package org.player.resource;

import org.player.entity.PlayerAntidote;
import org.player.entity.PlayerPlantPicture;
import org.player.service.PlayerPlantPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class PlayerPlantPictureResourceImpl implements PlayerPlantPictureResource {

    @Autowired
    private PlayerPlantPictureService playerPlantPictureService;

    // Create/Update
    @PostMapping(path = "/player-plant-picture/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerPlantPicture> savePicture(@RequestBody PlayerPlantPicture picture) {

        PlayerPlantPicture newPpp = playerPlantPictureService.savePlayerPlantPicture(picture);

        if(newPpp != null) {
            return new ResponseEntity<>(newPpp, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/player-plant-picture/{playerId}/{plantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerPlantPicture>> getPlayersPictureOfPlant(int playerId, int plantId) {
        List<PlayerPlantPicture> pppList = playerPlantPictureService.getPicturesByPlayerAndPlantId(playerId, plantId);
        if(pppList != null) {
            return new ResponseEntity<List<PlayerPlantPicture>>(pppList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/player-plant-picture/one/{pictureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerPlantPicture> getOnePicture(int pictureId) {

        PlayerPlantPicture ppPicture = playerPlantPictureService.getPlayerPlantPictureById(pictureId);
        if(ppPicture != null) {
            return new ResponseEntity<PlayerPlantPicture>(ppPicture, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
