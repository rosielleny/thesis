package org.druid.entity.original.key;

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
public class PlayerAntidoteKey implements Serializable {

    @Column(name="playerId")
    private int playerId;

    @Column(name = "antidoteId")
    private int antidoteId;


}
