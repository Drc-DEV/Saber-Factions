package com.massivecraft.factions.zcore.persist.json;

import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.persist.MemoryFPlayer;

public class JSONFPlayer extends MemoryFPlayer {

    public JSONFPlayer(MemoryFPlayer arg0) {
        super(arg0);
    }

    public JSONFPlayer(String id) {
        super(id);
    }

    @Override
    public void remove() {
        ((JSONFPlayers) FPlayers.getInstance()).fPlayers.remove(getId());
    }

    public boolean shouldBeSaved() {
        return this.hasFaction() || (this.getPowerRounded() != this.getPowerMaxRounded() && this.getPowerRounded() != (int) Math.round(Config.POWER_PLAYER_STARTING.getDouble()));
    }
}
