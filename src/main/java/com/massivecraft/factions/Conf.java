package com.massivecraft.factions;

import com.massivecraft.factions.zcore.fperms.DefaultPermissions;

import java.util.HashMap;

public class Conf {

    public static Backend backEnd = Backend.JSON;

    public static boolean useCustomDefaultPermissions = true;
    public static HashMap<String, DefaultPermissions> defaultFactionPermissions = new HashMap<>();
    private static transient Conf i = new Conf();

    static {
        // Is this called lazy load?
        defaultFactionPermissions.put("COLEADER", new DefaultPermissions(true));
        defaultFactionPermissions.put("MODERATOR", new DefaultPermissions(true));
        defaultFactionPermissions.put("NORMAL MEMBER", new DefaultPermissions(false));
        defaultFactionPermissions.put("RECRUIT", new DefaultPermissions(false));
        defaultFactionPermissions.put("ALLY", new DefaultPermissions(false));
        defaultFactionPermissions.put("ENEMY", new DefaultPermissions(false));
        defaultFactionPermissions.put("TRUCE", new DefaultPermissions(false));
        defaultFactionPermissions.put("NEUTRAL", new DefaultPermissions(false));
    }

    public static void load() {
        FactionsPlugin.getInstance().persist.loadOrSaveDefault(i, Conf.class, "conf");
    }

    public static void save() {
        FactionsPlugin.getInstance().persist.save(i);
    }

    public static void saveSync() {
        FactionsPlugin.instance.persist.saveSync(i);
    }

    public enum Backend {
        JSON
    }
}

