DROP SCHEMA IF EXISTS PlantEducation;
CREATE SCHEMA PlantEducation;
USE PlantEducation;

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
    plantId INT,
	medicinalInfo TEXT,
    culturalInfo TEXT,
    ecosystemInfo TEXT,
    scientificInfo TEXT,
    additionalInfo TEXT,
	FOREIGN KEY (plantId) REFERENCES Plant(plantId)
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

# Table holding actions that form the antidote making game
CREATE TABLE ActionType(
	actionId INT AUTO_INCREMENT PRIMARY KEY,
    actionType VARCHAR(255)
    );

# Table matching Antidotes to Actions
CREATE TABLE AntidoteAction(
	actionId INT,
    antidoteId INT,
	PRIMARY KEY (antidoteId, actionId),
	FOREIGN KEY (actionId) REFERENCES ActionType(actionId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId)
    );


# Table linking Plants to antidote, one antidote could have many plants,
# and one plant could be a part of many antidotes
CREATE TABLE PlantAntidote(
	plantId INT,
    antidoteId INT,
	PRIMARY KEY (antidoteId, plantId),
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId)
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
	plantId INT,
    antidoteId INT,
    questGiverId INT,
    patientId INT,
    startText TEXT,
    endText TEXT,
    requiredLevel TINYINT,
    xpValue TINYINT,
    stage1Text VARCHAR(255),
    stage2Text VARCHAR(255),
    stage3Text VARCHAR(255),
    FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    FOREIGN KEY (questGiverId) REFERENCES GameCharacter(gameCharacterId),
    FOREIGN KEY (patientId) REFERENCES GameCharacter(gameCharacterId)
    );

    
# The following tables allow for player progress to be saved
# Pictures players have taken of plants
CREATE TABLE PlayerPlantPicture(
	pictureId INT AUTO_INCREMENT PRIMARY KEY,
    plantId INT,
    playerId INT,
    picture VARCHAR(255),
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId) ON DELETE CASCADE
	);

# Plants the player has discovered
CREATE TABLE PlayerPlant(
    plantId INT,
    playerId INT,
    discoveredOrder INT,
	PRIMARY KEY (playerId, plantId),
	FOREIGN KEY (plantId) REFERENCES Plant(plantId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId) ON DELETE CASCADE
);
 
 # Antidotes the player has discovered, how many they have made,
 # how many they have used
 CREATE TABLE PlayerAntidote(
    antidoteId INT,
    playerId INT,
    numberMade INT,
    numberUsed INT,
    PRIMARY KEY(playerId, antidoteId),
	FOREIGN KEY (antidoteId) REFERENCES Antidote(antidoteId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId) ON DELETE CASCADE
 );
 
 # Quests the user has completed/activated/has unlocked
 CREATE TABLE PlayerQuest(
    questId INT,
    playerId INT,
    questStatus ENUM('Active', 'Inactive', 'Complete') NOT NULL,
    questStage ENUM('Beginning', 'PlantFound', 'AntidoteMade') NOT NULL,
	PRIMARY KEY(playerId, questId),
	FOREIGN KEY (questId) REFERENCES Quest(questId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId) ON DELETE CASCADE
 );
 
 CREATE TABLE GameLevel(
	gameLevelId INT AUTO_INCREMENT PRIMARY KEY,
    requiredXP INT 
    );
 
 
 CREATE TABLE Quiz(
	quizId INT AUTO_INCREMENT PRIMARY KEY,
	isExam BOOLEAN,
    quizName ENUM('Medicine', 'Identification', 'General') NOT NULL,
    questionNumber TINYINT,
    xpWorth INT
	);
    
CREATE TABLE QuestionTemplate(
	questionId INT AUTO_INCREMENT PRIMARY KEY,
    questionCategory ENUM('Medicine', 'Identification') NOT NULL,
    questionSubject ENUM('Plant', 'PlantPicture', 'Ailment') NOT NULL,
    questionAnswer ENUM('Plant', 'PlantPicture', 'Ailment') NOT NULL,
    questionText VARCHAR(255)
    );
    

    
 # TEST VALUES BELOW
 
 -- Inserting quizzes and questions
 INSERT INTO QuestionTemplate(questionCategory, questionSubject, questionAnswer, questionText)
 VALUES('Identification', 'Plant', 'PlantPicture', 'Identify the {Plant}'),
 ('Identification', 'PlantPicture', 'Plant','This plant is'),
 ('Medicine', 'Plant', 'Ailment', '{Plant} can be used to treat' ),
 ('Medicine', 'Ailment', 'PlantPicture', 'Which plant is a treatment for {Ailment}' );
 
  INSERT INTO Quiz(isExam, questionNumber, quizName ,xpWorth)
 VALUES (false, 10, 'General',2),
 (false, 10, 'Medicine',2),
 (false, 10, 'Identification',2); 
 
 -- Inserting a test plant
INSERT INTO Plant (plantName, plantLocation, defaultPicture, uniqueFeature1, uniqueFeature2, uniqueFeature3, treatmentFor, season)
VALUES ('Broad Leaf Dock', 'map-location', 'defaultDockLeaf.jpg', 'Look at the base of the plant - the leaves emerge from a basal rosette.', 'See the leaves? In broad leaf dock these should be smooth and oblong shaped.', 'Now look at the stocks: these are normally quite long.', 'Nettle stings', 'Summer'),
	('Burdock', 'map-location', 'defaultBurdock.jpg', 'Look at the leaves - their heartshaped appearance might be the reason for one of the plant\'s other names: Love Leaves.', 'Look more closely, the leaves should be dark green on top, and paler and a little downy on the underside.', 'See the flowers? They\'re a purple colour when in bloom, and dry out into a burr. These burrs get stuck in animals\' fur, helping to carry the seeds away from the parent plant.', 'Eczema', 'Summer-Autumn'),
    -- Below this point plants are chatgpt generated for testing purposes. Those above are hand researched and written. Those below need to be changed to handcrafted entries.
     ('Willow Herb', 'forest edges', 'willowHerb.jpg', 'Notice the unique spike of pink flowers.', 'Leaves are lance-shaped with a toothed margin.', 'The plant has a hairy stem which helps distinguish it.', 'Skin irritation', 'Spring-Summer'),
    ('Fireweed', 'burned clearings', 'fireweed.jpg', 'Easily recognized by its tall, dominant red flowers.', 'The leaves are spirally arranged, with a slightly reddish tinge.', 'The seeds have silky hairs that aid in wind dispersal.', 'Burns', 'Summer'),
    ('Chickweed', 'garden beds', 'chickweed.jpg', 'Small white star-shaped flowers are a key identifier.', 'The leaves are oval and grow in opposite pairs.', 'Stems are trailing and can root at the nodes.', 'Itchy skin', 'Spring-Autumn'),
    ('Hemlock', 'damp areas', 'hemlock.jpg', 'The plant has small white flowers arranged in umbels.', 'Its leaves are finely divided and fern-like.', 'Hemlock stems have purple spots.', 'Not applicable - poisonous', 'Late Spring'),
    ('Lavender', 'gardens', 'lavender.jpg', 'Known for its calming fragrance.', 'Produces small purple flowers on long spikes.', 'Leaves are narrow and gray-green in color.', 'Anxiety and sleep issues', 'Summer'),
    ('Dandelion', 'meadows', 'dandelion.jpg', 'Bright yellow flowers that turn into fluffy seed heads.', 'Leaves are jagged and grow in a rosette at the base.', 'Taproot is thick and can regenerate if not fully removed.', 'Digestive ailments', 'Spring-Autumn'),
    ('Stinging Nettle', 'rich soil near water', 'stingingNettle.jpg', 'Leaves have stinging hairs that cause irritation.', 'The small green flowers are wind-pollinated.', 'The plant is rich in vitamins A and C.', 'Arthritis pain', 'Spring-Fall'),
    ('Yarrow', 'fields', 'yarrow.jpg', 'Produces clusters of small white to pink flowers.', 'Leaves are finely divided and have a feathery appearance.', 'Known for its use in traditional medicine to staunch bleeding.', 'Colds and fevers', 'Summer'),
    ('Elderberry', 'woodlands', 'elderberry.jpg', 'Produces clusters of tiny white flowers.', 'Berries are dark purple and used in syrups and jams.', 'Leaves are compound with 5 to 7 leaflets.', 'Flu symptoms', 'Late Summer'),
    ('Mullein', 'disturbed soil', 'mullein.jpg', 'Tall stalk with dense yellow flowers.', 'Leaves are large, woolly, and basal.', 'The plant is biennial, forming a rosette in the first year and flowering in the second.', 'Respiratory problems', 'Summer');
    

INSERT INTO CompendiumPage (plantId, medicinalInfo, culturalInfo, ecosystemInfo, scientificInfo, additionalInfo)
VALUES (1, '	Dock leaves are commonly used in Britain and Ireland to treat nettle stings. 
Scientific studies on the effectiveness of this treatment have produced mixed results, but many people still attest to its usefulness. 
The dock leaf is squeezed or crushed to release its juices which are then applied to the affected area to reduce the itchiness and 
inflammation caused by a brush with a stinging nettle.',
'The belief that dock leaves can be used to treat nettle stings and be traced back thousands of years to ancient Greece, 
where Pedanius Dioscorides wrote that they could be used to treat irritation caused by other plants. This idea could in part stem 
from an old belief that if a plant caused an ailment, the cure for that ailment could always be found growing nearby, as dock leafs 
are often found near nettles. This was the same text that claimed plants that resembled certain human body parts could be used to 
treat illnesses affecting those body parts. These theories have since been disproven, but the idea that dock leaves treat nettle stings persists.
	Dock leaves are sometimes eaten by humans, though the leaves do contain oxalic acid which can be dangerous in high quantities. 
They are described as having a tart, lemony taste, with younger leaves being more pleasant and older leaves more sour. They can 
be used in salad, soups, or cooked like spinach, and the ground seeds can be used as flour. 
	In the United Kingdom, Dock plants are considered weeds, and in North America they are an invasive species. ',
    'Dock plants are an important food source for wildlife. Their leaves are loved by many insects, including 
caterpillars, and these insects in turn provide a food source for birds and hedgehogs.  In winter, the seeds provide nourishment 
for birds, rodents, and deer. The plant can adapt to many environments, from roadsides and waste land, to woodlands, shorelines and riverbanks.',
'The scientific name of Broad-Leaf Dock is Rumex obtusifolius. It can be identified by its large oval leaves which emerge from a basal rosette on long stems. 
The tops of the leaves are smooth while the underside can have small, soft hairs. The edges of the leaves are generally smooth or slightly wavy, while Rumex crisps, curly dock, 
has noticeably wavy leaves.', '' );

-- Inserting a test game character (quest giver and patient)
INSERT INTO GameCharacter (gameCharacterName, gameCharacterPicture)
VALUES ('Druid Rowan', 'druid_rowan.jpg');

-- Inserting a test antidote
INSERT INTO Antidote (antidoteName, antidotePicture, antidoteDescription)
VALUES ('Balm for Nettle Stings', 'dock_elixir.jpg', 'A popular remedy for nettle stings.'),
	('Poltice for Eczema', 'burdock_elicir.jpg', 'A soothing poltice made to reduce inflamation and prevent infection.');

-- Inserting a test action type for antidote making game
INSERT INTO ActionType (actionType)
VALUES ('Mashing');

-- Linking antidote to action
INSERT INTO AntidoteAction (actionId, antidoteId)
VALUES (1, 1),
		(1, 2);

-- Linking plants to antidote
INSERT INTO PlantAntidote (plantId, antidoteId)
VALUES (1, 1),
	(2,2);

-- Inserting a test player
INSERT INTO Player (playerName, playerEmail, playerPhone, playerPicture, playerTotalXP, playerLevel)
VALUES ('Jane Doe', 'janedoe@example.com', '123-456-7890', 'player1profile.png', 100, 1);

-- Inserting a quest involving the test plant, antidote, and game characters
INSERT INTO Quest (plantId, antidoteId, questGiverId, patientId, startText, endText, requiredLevel, xpValue, stage1Text, stage2Text, stage3Text)
VALUES (1, 1, 1, 1, 'There I was, head in the clouds, not realising I\'d walked into a huge patch of stinging nettles! Now I\'m just covered in stings. First things first, we\'ll need to find some dock leafs.', 
'Much better, thank you!', 1, 10, 'Find the Dock Leaves.', 'Prepare the antidote.', 'Deliver the antidote.'),
(2, 2, 1, 1, 'Your first patient is suffering from eczema. Fortunately I know the perfect plant - burdock. You should see its location on your map.', 'Excellent, the patient should be much more comfortable now.', 
 1, 12, 'Find the burdock plant.', 'Prepare the antidote.', 'Deliver the antidote.');

-- Inserting a picture taken by the player of the plant
INSERT INTO PlayerPlantPicture (plantId, playerId, picture)
VALUES (1, 1, 'dockLeaf.jpg'),
		(1,1, 'dockLeaf1.jpg'),
        (1,1, 'dockLeaf2.jpg'),
        (1, 1, 'dockLeaf3.jpg'),
        (2, 1, 'burdock.jpg'),
		(2,1, 'burdock2.jpg'),
        (2, 1, 'burdock3.jpg');

-- Inserting plant discovery by the player
INSERT INTO PlayerPlant (plantId, playerId, discoveredOrder)
VALUES (1, 1, 1),
	(2,1, 2);

-- Inserting antidote information for the player
INSERT INTO PlayerAntidote (antidoteId, playerId, numberMade, numberUsed)
VALUES (1, 1, 1, 1);

-- Inserting a player quest entry
INSERT INTO PlayerQuest (questId, playerId, questStatus, questStage)
VALUES (1, 1, 'Complete', 'AntidoteMade'),
		(2, 1, 'Inactive', 'Beginning');

INSERT INTO GameLevel (gameLevelId, requiredXP) VALUES 
(1, 100),
(2, 200),
(3, 400),
(4, 800),
(5, 1600),
(6, 3200),
(7, 6400),
(8, 12800),
(9, 25600),
(10, 51200);
