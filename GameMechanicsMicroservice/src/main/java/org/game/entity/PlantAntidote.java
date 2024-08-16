package org.game.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.game.entity.key.PlantAntidoteKey;

/* This entity reflects a database junction table joining plants to the
 * antidotes they are used in */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantAntidote {

    @EmbeddedId
    private PlantAntidoteKey plantAntidoteKey;
}
