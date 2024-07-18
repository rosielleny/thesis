DROP SCHEMA IF EXISTS PlantEducation;
CREATE SCHEMA PlantEducation;
USE PlantEducation;

DROP TABLE IF EXISTS Plant;
DROP TABLE IF EXISTS CompendiumParagraphs;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Antidote;
DROP TABLE IF EXISTS PlantAntidote;

# Holds information about plants to be used in quests
CREATE TABLE Plant(
	plantId INT AUTO_INCREMENT PRIMARY KEY,
    plantName VARCHAR(255) NOT NULL,
    plantLocation VARCHAR(255),
    defaultPicture VARCHAR(255),
    uniqueFeature1 VARCHAR(255),
    uniqueFeature2 VARCHAR(255),
    uniqueFeature3 VARCHAR(255),
    treatementFor VARCHAR(255),
    season VARCHAR(255)
); 

# Holds large amounts of plant information categorised by subject to be used in
# the compendium
CREATE TABLE CompendiumParagraphs(
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
	medicinalInfo TEXT,
    culturalInfo TEXT,
    ecosystemInfo TEXT,
    scientificInfo TEXT,
    additionalInfo TEXT
    );
    
    
# Table holding player profile information, representing the player as an entity in the game
CREATE TABLE Player(
	playerId INT AUTO_INCREMENT PRIMARY KEY,
    playerName VARCHAR(255),
    playerEmail VARCHAR(255),
    playerPhone VARCHAR(255),
    playerPicture VARCHAR(255),
    playerTotalXP BIGINT UNSIGNED DEFAULT 0,
    playerLevel TINYINT
    );
    
# Table holding antidote information
CREATE TABLE Antidote(
	antidoteId INT AUTO_INCREMENT PRIMARY KEY,
    antidoteName VARCHAR(255),
    antidotePicture VARCHAR(255),
    antidoteDescription VARCHAR(255)
    );

# Table linking Plants to antidote, one antidote could have many plants,
# and one plant could be a part of many antidotes
CREATE TABLE PlantAntidote(
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId)
    );

# Table holding quest information
CREATE TABLE Quest(
	questId INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    FOREIGN KEY (questGiverId) REFERENCES GameCharacter(gameCharacterId),
    FOREIGN KEY (patientId) REFERENCES GameCharacter(gameCharacterId),
    startText TEXT,
    endText TEXT,
    requiredLevel TINYINT,
    xpValue TINYINT
    );
    
# Table holding information for game npcs
CREATE TABLE GameCharacter(
	gameCharacterId INT AUTO_INCREMENT PRIMARY KEY,
    gameCharacterName VARCHAR(255),
    gameCharacterPicture VARCHAR(255)
);

 