package org.player.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPlantKey  implements Serializable {

    @Column(name="playerId")
    private int playerId;

    @Column(name = "plantId")
    private int plantId;

}
