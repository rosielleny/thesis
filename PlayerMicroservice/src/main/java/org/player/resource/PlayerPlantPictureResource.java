package org.player.resource;

import org.player.entity.PlayerPlantPicture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlayerPlantPictureResource {

    public ResponseEntity<PlayerPlantPicture> savePicture(@RequestBody PlayerPlantPicture picture);
    public ResponseEntity<List<PlayerPlantPicture>> getPlayersPictureOfPlant(@PathVariable int playerId, @PathVariable int plantId);
    public ResponseEntity<PlayerPlantPicture> getOnePicture(@PathVariable int pictureId);

}
