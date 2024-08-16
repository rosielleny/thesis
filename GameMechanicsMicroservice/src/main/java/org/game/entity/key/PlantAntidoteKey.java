package org.game.entity.key;

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
public class PlantAntidoteKey  implements Serializable {

    @Column(name="plantId")
    private int plantId;

    @Column(name = "antidoteId")
    private int antidoteId;

}
