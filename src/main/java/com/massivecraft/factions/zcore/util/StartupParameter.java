package com.massivecraft.factions.zcore.util;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.massivecraft.factions.*;
import com.massivecraft.factions.cmd.Aliases;
import com.massivecraft.factions.cmd.audit.FLogManager;
import com.massivecraft.factions.cmd.reserve.ListParameterizedType;
import com.massivecraft.factions.cmd.reserve.ReserveObject;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.factions.integration.dynmap.EngineDynmap;
import com.massivecraft.factions.util.Logger;
import com.massivecraft.factions.util.timer.TimerManager;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.file.impl.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pw.saber.corex.CoreX;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StartupParameter {

    public static void initData(FactionsPlugin plugin) {

        FactionsPlugin.getInstance().fileManager = new FileManager();
        FactionsPlugin.getInstance().fileManager.setupFiles();

        FactionsPlugin.getInstance().fLogManager = new FLogManager();

        FPlayers.getInstance().load();
        Factions.getInstance().load();

        for (FPlayer fPlayer : FPlayers.getInstance().getAllFPlayers()) {
            Faction faction = Factions.getInstance().getFactionById(fPlayer.getFactionId());
            if (faction == null) {
                Logger.print("Invalid faction id on " + fPlayer.getName() + ":" + fPlayer.getFactionId(), Logger.PrefixType.WARNING);
                fPlayer.resetFactionData(false);
                continue;
            }
            if (fPlayer.isAlt()) faction.addAltPlayer(fPlayer);
            else faction.addFPlayer(fPlayer);
        }

        Factions.getInstance().getAllFactions().forEach(Faction::refreshFPlayers);

        Board.getInstance().load();
        Board.getInstance().clean();

        Aliases.load();
        EngineDynmap.getInstance().init();

        if (Bukkit.getPluginManager().isPluginEnabled("LunarClient-API")) {
            FactionsPlugin.getInstance().lunarClientAPI = LunarClientAPI.getInstance();
            Logger.print("Implementing Lunar Client Integration", Logger.PrefixType.DEFAULT);
        }

        FactionsPlugin.getInstance().hookedPlayervaults = setupPlayerVaults();

        Econ.setup();

        initReserves();

        FactionsPlugin.cachedRadiusClaim = Config.FACTION_CLAIMRADIUS_ENABLED.getOption();

        CoreX.init();

        FactionsPlugin.getInstance().fLogManager.loadLogs(plugin);

        FactionsPlugin.getInstance().timerManager = new TimerManager(plugin);
        FactionsPlugin.getInstance().timerManager.reloadTimerData();
        Logger.print("Loaded " + FactionsPlugin.getInstance().timerManager.getTimers().size() + " timers into list!", Logger.PrefixType.DEFAULT);

    }

    public static void initReserves() {
        FactionsPlugin.getInstance().reserveObjects = new ArrayList<>();
        String path = Paths.get(FactionsPlugin.getInstance().getDataFolder().getAbsolutePath()).toAbsolutePath() + File.separator + "data" + File.separator + "reserves.json";
        File file = new File(path);
        try {
            String json;
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            json = String.join("", Files.readAllLines(Paths.get(file.getPath()))).replace("\n", "").replace("\r", "");
            if (json.equalsIgnoreCase("")) {
                Files.write(Paths.get(path), "[]".getBytes());
                json = "[]";
            }
            FactionsPlugin.getInstance().reserveObjects = FactionsPlugin.getInstance().getGsonBuilder().create().fromJson(json, new ListParameterizedType(ReserveObject.class));
            if (FactionsPlugin.getInstance().reserveObjects == null)
                FactionsPlugin.getInstance().reserveObjects = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean setupPlayerVaults() {
        Plugin plugin = FactionsPlugin.getInstance().getServer().getPluginManager().getPlugin("PlayerVaults");
        return plugin != null && plugin.isEnabled();
    }
}
