package org.druid.entity.original;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.player.entity.key.PlayerPlantKey;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPlant {

    @EmbeddedId
    private PlayerPlantKey playerPlantKey;
}
