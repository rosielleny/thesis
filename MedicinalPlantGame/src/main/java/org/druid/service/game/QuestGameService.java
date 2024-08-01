package org.druid.service.game;

import org.druid.entity.composite.QuestGame;

import java.util.List;
// Collates data from original entities
public interface QuestGameService {

    QuestGame getQuestGame(int questId, int playerId);

}
