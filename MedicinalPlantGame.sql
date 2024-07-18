DROP SCHEMA IF EXISTS PlantEducation;
CREATE SCHEMA PlantEducation;
USE PlantEducation;

DROP TABLE IF EXISTS Plant;
DROP TABLE IF EXISTS CompendiumPage;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Antidote;
DROP TABLE IF EXISTS PlantAntidote;
DROP TABLE IF EXISTS GameCharacter;
DROP TABLE IF EXISTS Quest;
DROP TABLE IF EXISTS PlayerPlantPictures;
DROP TABLE IF EXISTS PlayerPlants;
DROP TABLE IF EXISTS PlayerAntidotes;
DROP TABLE IF EXISTS PlayerQuests;

# Holds information about plants to be used in quests
CREATE TABLE Plant(
	plantId INT AUTO_INCREMENT PRIMARY KEY,
    plantName VARCHAR(255) NOT NULL,
    plantLocation VARCHAR(255),
    defaultPicture VARCHAR(255),
    uniqueFeature1 VARCHAR(255),
    uniqueFeature2 VARCHAR(255),
    uniqueFeature3 VARCHAR(255),
    treatmentFor VARCHAR(255),
    season VARCHAR(255)
); 

# Holds large amounts of plant information categorised by subject to be used in
# the compendium
CREATE TABLE CompendiumPage(
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    plantId INT,
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
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    plantId INT,
    antidoteId INT
    );

# Table holding information for game npcs
CREATE TABLE GameCharacter(
	gameCharacterId INT AUTO_INCREMENT PRIMARY KEY,
    gameCharacterName VARCHAR(255),
    gameCharacterPicture VARCHAR(255)
);

# Table holding quest information
CREATE TABLE Quest(
	questId INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    FOREIGN KEY (questGiverId) REFERENCES GameCharacter(gameCharacterId),
    FOREIGN KEY (patientId) REFERENCES GameCharacter(gameCharacterId),
    plantId INT,
    antidoteId INT,
    questGiverId INT,
    patientId INT,
    startText TEXT,
    endText TEXT,
    requiredLevel TINYINT,
    xpValue TINYINT
    );
    
# The following tables allow for player progress to be saved
# Pictures players have taken of plants
CREATE TABLE PlayerPlantPictures(
	pictureId INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId),
    plantId INT,
    playerId INT,
    picture VARCHAR(255)
	);

# Plants the player has discovered
CREATE TABLE PlayerPlants(
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId),
    plantId INT,
    playerId INT
);
 
 # Antidotes the player has discovered, how many they have made,
 # how many they have used
 CREATE TABLE PlayerAntidotes(
	FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId),
    antidoteId INT,
    playerId INT,
    numberMade INT,
    numberUsed INT
 );
 
 # Quests the user has completed/activated/has unlocked
 CREATE TABLE PlayerQuests(
	FOREIGN KEY (questId) REFERENCES Quest(questId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId),
    questId INT,
    playerId INT,
    questStatus ENUM('active', 'inactive', 'complete') NOT NULL
 );