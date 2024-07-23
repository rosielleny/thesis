package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private int questionId;
    private String questionType; // Eg. True/False, Multiple Choice Image, Multiple Choice Text
    private String question; // Question text eg. "Identify the {plantName}". Answers will be taken from PlayerPlants.
}
