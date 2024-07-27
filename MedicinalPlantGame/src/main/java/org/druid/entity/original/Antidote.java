package org.druid.entity.original;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This entity reflects the database table of the same name
* It describes one antidote and is linked to the plants involved via
* the PlantAntidote table/entity */
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
