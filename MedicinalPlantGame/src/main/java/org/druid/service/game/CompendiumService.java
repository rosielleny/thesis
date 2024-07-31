package org.druid.service.game;

import org.druid.entity.composite.CompendiumEntry;

public interface CompendiumService {

   CompendiumEntry getCompendiumEntry(int plantId, int playerId);
}
