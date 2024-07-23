package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Antidote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int antidoteId;

    private String antidoteName;
    private String antidotePicture;
    private String antidoteDescription;
}
