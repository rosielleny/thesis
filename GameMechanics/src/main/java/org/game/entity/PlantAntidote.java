package org.game.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.game.entity.key.PlantAntidoteKey;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantAntidote {

    @EmbeddedId
    private PlantAntidoteKey plantAntidoteKey;
}
