package org.druid.service.game;

import org.druid.entity.composite.PlayerProfile;

public interface PlayerProfileService {

    PlayerProfile getPlayerProfile(int playerId);
}
