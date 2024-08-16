DROP SCHEMA IF EXISTS PlantEducation;
CREATE SCHEMA PlantEducation;
USE PlantEducation;

# Holds information about plants to be used in quests
CREATE TABLE Plant(
	plantId INT AUTO_INCREMENT PRIMARY KEY,
    plantName VARCHAR(255) NOT NULL,
    plantLocationT TINYINT,
    plantLocationL TINYINT,
    plantDescription VARCHAR(255),
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
    playerLevel TINYINT,
    playerCanDoExam BOOLEAN
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
    questName INT,
    patientId INT,
    startText TEXT,
    endText TEXT,
    requiredLevel TINYINT,
    xpValue INT,
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
    questionNumber TINYINT,
    xpWorth INT
	);
    
CREATE TABLE Question(
	questionId INT AUTO_INCREMENT PRIMARY KEY,
    questionCategory ENUM('Medicine', 'Identification') NOT NULL,
    questionSubject ENUM('Plant', 'PlantPicture', 'Ailment') NOT NULL,
    questionAnswer ENUM('Plant', 'PlantPicture', 'Ailment') NOT NULL,
    question VARCHAR(255)
    );
    
 -- TEST VALUES BELOW
 
 -- Inserting quizzes and questions
 INSERT INTO Question(questionCategory, questionSubject, questionAnswer, question)
 VALUES('Identification', 'Plant', 'PlantPicture', 'Identify the {Plant}'),
 ('Identification', 'PlantPicture', 'Plant','This plant is a'),
 ('Medicine', 'Plant', 'Ailment', '{Plant} can be used to treat' ),
 ('Medicine', 'Ailment', 'PlantPicture', 'Which plant is a treatment for {Ailment}' );
 

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
 (false, 10, 'Identification',2),
 (true, 20, 'General', 10); 
 
 -- Inserting a test plant

INSERT INTO Plant (plantName, plantLocationT, plantLocationL, defaultPicture, uniqueFeature1, uniqueFeature2, uniqueFeature3, treatmentFor, season, plantDescription)
VALUES ('Broad Leaf Dock', 20, 10, 'defaultDockLeaf.jpg', 'Look at the base of the plant - the leaves emerge from a basal rosette.', 'See the leaves? In broad leaf dock these should be smooth and oblong shaped.', 'Now look at the stocks: these are normally quite long.', 'Nettle stings', 'Summer', 'The plant you are looking for has large, oblong leaves. It grows on the ground up from a basal roset and is often found near stinging nettles.'),
	('Burdock', 30, 10, 'defaultBurdock.jpg', 'Look at the leaves - their heartshaped appearance might be the reason for one of the plant\'s other names: Love Leaves.', 'Look more closely, the leaves should be dark green on top, and paler and a little downy on the underside.', 'See the flowers? They\'re a purple colour when in bloom, and dry out into a burr. These burrs get stuck in animals\' fur, helping to carry the seeds away from the parent plant.', 'Eczema', 'Summer-Autumn', 'The plant you are looking for as distinctive purple flowers which are round and spikey. The smaller leaves are heart shaped and the larger leaves more spear shaped.'),

     ('St John\'s Wort', 50, 70, 'defaultStJohnsWort.jpg', 'Have a look at the flowers. They have five distinct yellow petals.', 'Now look more closely at the stems, they should be woody and reddish.', 'See the wee leaves? They\'re oblong, and about three times as long as they are wide.', 'Anxiety and low mood', 'Summer', 'The plant you\'re looking for has small leaves clustered around woody stems and bright yellow flowers.'),
        -- Below this point the additional details of the plant need to be filled in before being used in quests
    ('Black Elderflower', 20, 10, 'defaultBlackElderflower.jpg', '', '', '', 'The Common Cold', '', ''),
    ('Sweet Wormwood', 20, 10, 'defaultSweetWormwood.jpg', '', '', '', 'Malaria', '', ''),
    ('Lungwort', 20, 10, 'defaultLungwort.jpg', '', '.', '', 'Lung problems', '', ''),
    ('Poppy', 20, 10, 'defaultPoppy.jpg', '', '', '', 'Pain', '', ''),
    ('Thyme', 20, 10, 'defaultThyme.jpg', '', '', '', 'Airway inflamation', '', ''),
    ('Garlic', 20, 10, 'defaultGarlic.jpg', '', '', '', 'Inflamation and Infection', '', ''),
    ('Snowdrop', 20, 10, 'defaultSnowdrop.jpg', '', '', '', 'Memory impairment', '', ''),
    ('Willow', 20, 10, 'defaultWillow.jpg', '', '', '', 'Fever', '', ''),
    ('Foxglove', 20, 10, 'defaultFoxglove.jpg', '', '.', '', 'Abnormal heart rhythm', 'Summer', '');

    

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
The tops of the leaves are smooth while the underside can have small, soft hairs. The edges of the leaves are generally smooth or slightly wavy, while Rumex crispus, curly dock, 
has noticeably wavy leaves.', '' ),
(2, 'Burdock roots can be used to treat eczema. The root is ground down, usually in a mortar and pestle or grater. Home remedies then involve mixing the crushed burdock with ground oats and lemon juice. Travelling people also use the leaves and flowers as a cure for rheumatism, sometimes as a treatment and sometimes as a magical ward against developing it.', 
'Burdock has many different names in Britain. “Stickelburr” comes from the Old English “sticke”, meaning prickle, and “bur”, describing the plant’s seed heads. Other names include “Clotbur”, “Beggar’s Buttons”, “Burrseed”, “Love Leaves”, “Louse Bur”, and “Fox’s Clote”.
Cornish folklore describes how the ‘piskies’, a type of fairy, like to cause mischief during the night by riding young horses through the fields and deliberately tangling their manes up with the burrs from the burdock plant.
In West Lothian the burrs from the burdock are used in the creation of the Burry Man costume. The Burry Man appears as a humanoid covered in burdock burrs from head to toe. He visits homes each year on the second Friday of August. He is welcomed by the townsfolk and given gifts of money. The Burry Man costume takes two hours to put on, after which the wearer walks very slowly and must drink through a straw. ', 
'Burdock is found across the world but is native to Europe and Asia, and considered invasive in parts of North America. It can usually be found at the edges of fields, around fences, railway tacks, roadsides, and at the edges of streams and woodland. Many species of moth use the plant for food, with the larva of the ghost moth in particular feeding on the plants roots. However, the burdock can be a danger to birds who can become tangled up in the burrs and struggle to free themselves. The burrs are designed to catch in the coats of animals so that the animals carry the seeds far and wide, dispersing them away from the parent plant. The sticking quality of these burdock heads is said to have inspired hook and loop fasteners, more commonly known as Velcro.',
 'The scientific name of Burdock is Arctium lappa or Arctium minus. The two subspecies are very similar, but Arctium minus is more widespread. It can be identified by its leaves which are heart-shaped near the base and spear-shaped on the bloom stem. The tops of the leaves are smooth while the underside is paler and slightly downy. The flowers are similar to the thistle with their purple tops but dry into a brown burr. The roots are usually long and black and can grow to be quite large.',
 '')
 (3, 'St John’s Wort can be used as an antidepressant for mild to moderate cases. It as been found to be more effective than placebo and just as effective as other antidepressant in mild/moderate cases. It can interact very badly with other medications however, so must be used with care. ',
 'St John’s Wort got its name from the time of year it flowers. It is usually in bloom around Midsummer’s day, otherwise known as the Feast of St John. It is also known as ‘Balm of the Warrior’s Wound’, ‘Rose of Sharon’, and. ‘Aaron’s beard’. In European folk magic, St John’s Wort is used for protection against evil. One way to use it for this purpose was to burn it on the midsummer bonfire and leap through the scented smoke. To protect against fairies, the plant would be gathered at midnight on St John’s Eve and then hung above the entrance to the home, preventing fairies from crossing the threshold.',
 'St Johns Wort is native to Britain and most commonly found in England and Wales, but some species also come from Turkey and the Mediterranean. The flowers of some of the plants are a food source for moth larvae of the Mottled Beuty, Bright-line Brown-eye, and V-Pug moths.',
 'St John’s Wort plants all belong to the family Hypericaceae and all share the distinctive yellow flowers.  The active ingredients used in anti-depressants are Hypericin and Hyperforin.',
 '');

-- Inserting a test game character (quest giver and patient)
INSERT INTO GameCharacter (gameCharacterName, gameCharacterPicture)
VALUES ('Druid Rowan', 'druidMentor.png');

-- Inserting a test antidote
INSERT INTO Antidote (antidoteName, antidotePicture, antidoteDescription)
VALUES ('Balm for Nettle Stings', 'dock_elixir.jpg', 'A popular remedy for nettle stings consisting of crushed dock leaves.'),
	('Poultice for Eczema', 'burdock_elixir.jpg', 'A soothing burdock poultice made to reduce inflamation and prevent infection.'),
    ('Tonic for the Mood', 'stJohn_elixir.jpg', 'A tonic of St John\'s Wort to lift the mood.');


-- Inserting a test action type for antidote making game
INSERT INTO ActionType (actionType)
VALUES ('Mashing');

-- Linking antidote to action
INSERT INTO AntidoteAction (actionId, antidoteId)
VALUES (1, 1),
		(1, 2),
        (1, 3);

-- Linking plants to antidote
INSERT INTO PlantAntidote (plantId, antidoteId)
VALUES (1, 1),
	(2,2),
    (3,3);

-- Inserting a test player
INSERT INTO Player (playerName, playerEmail, playerPhone, playerPicture, playerTotalXP, playerLevel, playerCanDoExam)
VALUES ('Jane Doe', 'janedoe@example.com', '123-456-7890', 'player1profile.png', 100, 1, false);

-- Inserting a quest involving the test plant, antidote, and game characters
INSERT INTO Quest (plantId, antidoteId, questGiverId, patientId, startText, endText, requiredLevel, xpValue, stage1Text, stage2Text, stage3Text)

VALUES (1, 1, 1, 1, 'There I was, head in the clouds, not realising I\'d walked into a huge patch of stinging nettles! Now I\'m just covered in stings. First things first, we\'ll need to find some dock leaves. I think I saw some of there - I\'ve marked the location on your map.', 
'The stings are much better now, thank you!', 1, 10, 'Find the Dock Leaves.', 'Prepare the antidote.', 'Deliver the antidote.'),
(2, 2, 1, 1, 'Your patient is suffering from uncomfortable eczema. Fortunately, I know the perfect plant - burdock! Its anti-inflamatory and antibacterial properties make it the perfect treatment. You should see its location on your map.', 'Excellent, the patient should be much more comfortable now.', 
 1, 12, 'Find the burdock plant.', 'Prepare the antidote.', 'Deliver the antidote.'),
 (3,3,1,1, 'Your patient has been feeling a low lately. Some St John\'s Wort should help them feel a wee bit better. I\'ve marked its location on your map.',
 'This should help pep them up - well done!', 1, 2, 'Find the St John\'s Wort at the location on your map.', 'Prepare the antidote.', 'Deliver the antidote.' );




-- Inserting a picture taken by the player of the plant
INSERT INTO PlayerPlantPicture (plantId, playerId, picture)
VALUES (1, 1, 'dockLeaf.jpg'),
		(1,1, 'dockLeaf1.jpg'),
        (1,1, 'dockLeaf2.jpg'),
        (1, 1, 'dockLeaf3.jpg'),
        (2, 1, 'burdock.jpg'),
        (2,1, 'burdock1.jpg'),
		(2,1, 'burdock2.jpg'),
        (2, 1, 'burdock3.jpg'),
        (3, 1, 'stJohnsWort.jpg'),
        (3,1, 'stJohnsWort1.jpg'),
		(3,1, 'stJohnsWort2.jpg'),
        (3, 1, 'stJohnsWort3.jpg');

-- Inserting plant discovery by the player
-- INSERT INTO PlayerPlant (plantId, playerId, discoveredOrder)
-- VALUES (1, 1, 1);

-- Inserting antidote information for the player
-- INSERT INTO PlayerAntidote (antidoteId, playerId, numberMade, numberUsed)
-- VALUES (1, 1, 1, 1);

-- Inserting a player quest entry
INSERT INTO PlayerQuest (questId, playerId, questStatus, questStage)
VALUES (1, 1, 'Inactive', 'Beginning'),
		(2, 1, 'Inactive', 'Beginning'),
        (3, 1, 'Inactive', 'Beginning');


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

