package com.massivecraft.factions.util.tasks;

import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.cmd.CmdFly;
import com.massivecraft.factions.zcore.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FactionsTask implements Runnable {

    public FactionsTask(Plugin bukkitPlugin) {
        Bukkit.getScheduler().runTaskTimer(bukkitPlugin, this, 20L, 20L);
    }

    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (Config.FLY_ENABLED.getOption()) {
                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                boolean enemiesNearby = fPlayer.checkIfNearbyEnemies();
                if (fPlayer.isFlying()) {
                    if (fPlayer.isAdminBypassing()
                            || player.isOp()
                            || player.getGameMode() == GameMode.CREATIVE
                            || player.getGameMode() == GameMode.SPECTATOR) continue;
                    FLocation fLocation = new FLocation(player.getLocation());
                    if (!fPlayer.canFlyAtLocation(fLocation)) {
                        fPlayer.setFlying(false, false);
                    }

                } else if (fPlayer.canFlyAtLocation()
                        && Config.FLY_AUTO_ENABLE.getOption()
                        && (!enemiesNearby || !Config.FLY_NEARBY_ENEMIES_CANCEL.getOption())
                        && System.currentTimeMillis() - fPlayer.getLastInCombat() > Config.FLY_COMBAT_COOLDOWN.getInt() * 1000L
                        && !CmdFly.falseList.contains(player.getUniqueId())) {
                    fPlayer.setFlying(true);
                }
            }
        }
    }
}
