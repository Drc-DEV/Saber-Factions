package com.massivecraft.factions.tag;

import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.util.timer.TimerManager;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.util.TL;
import org.bukkit.Bukkit;

import java.util.function.Supplier;

public enum GeneralTag implements Tag {

    GRACE_TIMER("{grace-time}", () -> String.valueOf(TimerManager.getRemaining(FactionsPlugin.getInstance().getTimerManager().graceTimer.getRemaining(), true))),
    MAX_WARPS("{max-warps}", () -> String.valueOf(Config.FACTION_WARPS_LIMIT.getInt())),
    MAX_ALLIES("{max-allies}", () -> getRelation("ally")),
    MAX_ENEMIES("{max-enemies}", () -> getRelation("enemy")),
    MAX_TRUCES("{max-truces}", () -> getRelation("truce")),
    FACTIONLESS("{factionless}", () -> String.valueOf(FPlayers.getInstance().getOnlinePlayers().stream().filter(p -> !p.hasFaction()).count())),
    FACTIONLESS_TOTAL("{factionless-total}", () -> String.valueOf(FPlayers.getInstance().getAllFPlayers().stream().filter(p -> !p.hasFaction()).count())),
    TOTAL_ONLINE("{total-online}", () -> String.valueOf(Bukkit.getOnlinePlayers().size())),
    ;

    private final String tag;
    private final Supplier<String> supplier;

    GeneralTag(String tag, Supplier<String> supplier) {
        this.tag = tag;
        this.supplier = supplier;
    }

    private static String getRelation(String relation) {
        if (FactionsPlugin.getInstance().getConfig().getBoolean("max-relations.enabled", true)) {
            return String.valueOf(FactionsPlugin.getInstance().getConfig().getInt("max-relations." + relation, 10));
        }
        return TL.GENERIC_INFINITY.toString();
    }

    public static String parse(String text) {
        for (GeneralTag tag : GeneralTag.values()) {
            text = tag.replace(text);
        }
        return text;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public boolean foundInString(String test) {
        return test != null && test.contains(this.tag);
    }

    public String replace(String text) {
        if (!this.foundInString(text)) {
            return text;
        }
        String result = this.supplier.get();
        return result == null ? null : text.replace(this.tag, result);
    }
}
