package org.plant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plantId;

    private String plantName;
    private String defaultPicture;
    private String uniqueFeature1; // Used as part of the quest to deliver fun facts
    private String uniqueFeature2;
    private String uniqueFeature3;
    private String treatmentFor; // Used in the compendium page
    private String season; // Used in the compendium page, and potentially to filter plants
    private byte plantLocationT; // Used to mark the plant on the botanic gardens map
    private byte plantLocationL;// Used to mark the plant on the botanic gardens map
    private String plantDescription;
}