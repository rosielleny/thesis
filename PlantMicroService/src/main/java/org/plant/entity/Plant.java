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
    private String plantLocation;
    private String defaultPicture;
    private String uniqueFeature1;
    private String uniqueFeature2;
    private String uniqueFeature3;
    private String treatmentFor;
    private String season;
}