package org.player.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPlantPicture {

    @Id
    private int pictureId;
    @Column(name = "playerId")
    private int playerId;
    @Column(name = "plantId")
    private int plantId;
    @Column(name = "picture")
    private String picture;
}
