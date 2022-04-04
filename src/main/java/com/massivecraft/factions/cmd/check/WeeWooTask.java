package com.massivecraft.factions.cmd.check;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.zcore.util.TL;
import org.bukkit.scheduler.BukkitRunnable;

public class WeeWooTask extends BukkitRunnable {

    private final String factionID;

    public WeeWooTask(Faction f) {
        this.factionID = f.getId();
    }

    @Override
    public void run() {
        Faction faction = Factions.getInstance().getFactionById(factionID);
        if (faction == null || !faction.isNormal()) {
            this.cancel();
            return;
        }
        if (!faction.isWeeWoo() || faction.getOnlinePlayers().isEmpty()) {
            faction.setWeeWoo(false);
            this.cancel();
            return;
        }
        faction.msg(TL.WEE_WOO_MESSAGE);
    }
}
