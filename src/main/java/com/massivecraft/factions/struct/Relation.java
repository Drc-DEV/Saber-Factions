package com.massivecraft.factions.struct;

import com.cryptomorin.xseries.XMaterial;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.fperms.Permissable;
import com.massivecraft.factions.zcore.util.TL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public enum Relation implements Permissable {

    /**
     * @author FactionsUUID Team - Modified By CmdrKittens
     */


    MEMBER(4, TL.RELATION_MEMBER_SINGULAR.toString()),
    ALLY(3, TL.RELATION_ALLY_SINGULAR.toString()),
    TRUCE(2, TL.RELATION_TRUCE_SINGULAR.toString()),
    NEUTRAL(1, TL.RELATION_NEUTRAL_SINGULAR.toString()),
    ENEMY(0, TL.RELATION_ENEMY_SINGULAR.toString());

    public final int value;
    public final String nicename;

    Relation(final int value, final String nicename) {
        this.value = value;
        this.nicename = nicename;
    }

    public static Relation fromString(String s) {
        // Because Java 6 doesn't allow String switches :(
        // We should use name here. Since most of features use name as identifier.
        if (s.equalsIgnoreCase(MEMBER.name())) {
            return MEMBER;
        } else if (s.equalsIgnoreCase(ALLY.name())) {
            return ALLY;
        } else if (s.equalsIgnoreCase(TRUCE.name())) {
            return TRUCE;
        } else if (s.equalsIgnoreCase(ENEMY.name())) {
            return ENEMY;
        } else {
            return NEUTRAL; // If they somehow mess things up, go back to default behavior.
        }
    }

    @Override
    public String toString() {
        return this.nicename;
    }

    public String getTranslation() {
        try {
            return TL.valueOf("RELATION_" + name() + "_SINGULAR").toString();
        } catch (IllegalArgumentException e) {
            return toString();
        }
    }

    public String getPluralTranslation() {
        for (TL t : TL.values()) {
            if (t.name().equalsIgnoreCase("RELATION_" + name() + "_PLURAL")) {
                return t.toString();
            }
        }
        return toString();
    }

    public boolean isMember() {
        return this == MEMBER;
    }

    public boolean isAlly() {
        return this == ALLY;
    }

    public boolean isTruce() {
        return this == TRUCE;
    }

    public boolean isNeutral() {
        return this == NEUTRAL;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }

    public boolean isAtLeast(Relation relation) {
        return this.value >= relation.value;
    }

    public boolean isAtMost(Relation relation) {
        return this.value <= relation.value;
    }

    public ChatColor getColor() {

        switch (this) {
            case MEMBER:
                return ChatColor.valueOf(Config.COLOR_MEMBER.getString());
            case ALLY:
                return ChatColor.valueOf(Config.COLOR_ALLY.getString());
            case NEUTRAL:
                return ChatColor.valueOf(Config.COLOR_NEUTRAL.getString());
            case TRUCE:
                return ChatColor.valueOf(Config.COLOR_TRUCE.getString());
            default:
                return ChatColor.valueOf(Config.COLOR_ENEMY.getString());
        }
    }

    // return appropriate Conf setting for DenyBuild based on this relation and their online status
    public boolean confDenyBuild(boolean online) {
        if (isMember()) {
            return false;
        }

        if (online) {
            if (isEnemy()) {
                return Config.CLAIMS_ENEMY_DENYBUILD.getOption();
            } else if (isAlly()) {
                return Config.CLAIMS_ALLY_DENYBUILD.getOption();
            } else if (isTruce()) {
                return Config.CLAIMS_TRUCE_DENYBUILD.getOption();
            } else {
                return Config.CLAIMS_DENYBUILD.getOption();
            }
        } else {
            if (isEnemy()) {
                return Config.CLAIMS_ENEMY_DENYBUILD_OFFLINE.getOption();
            } else if (isAlly()) {
                return Config.CLAIMS_ALLY_DENYBUILD_OFFLINE.getOption();
            } else if (isTruce()) {
                return Config.CLAIMS_TRUCE_DENYBUILD_OFFLINE.getOption();
            } else {
                return Config.CLAIMS_DENYBUILD_OFFLINE.getOption();
            }
        }
    }

    // return appropriate Conf setting for PainBuild based on this relation and their online status
    public boolean confPainBuild(boolean online) {
        if (isMember()) {
            return false;
        }

        if (online) {
            if (isEnemy()) {
                return Config.CLAIMS_ENEMY_PAINBUILD.getOption();
            } else if (isAlly()) {
                return Config.CLAIMS_ALLY_PAINBUILD.getOption();
            } else if (isTruce()) {
                return Config.CLAIMS_TRUCE_PAINBUILD.getOption();
            } else {
                return Config.CLAIMS_PAINBUILD.getOption();
            }
        } else {
            if (isEnemy()) {
                return Config.CLAIMS_ENEMY_PAINBUILD_OFFLINE.getOption();
            } else if (isAlly()) {
                return Config.CLAIMS_ALLY_PAINBUILD_OFFLINE.getOption();
            } else if (isTruce()) {
                return Config.CLAIMS_TRUCE_PAINBUILD_OFFLINE.getOption();
            } else {
                return Config.CLAIMS_PAINBUILD_OFFLINE.getOption();
            }
        }
    }

    // return appropriate Conf setting for DenyUseage based on this relation
    public boolean confDenyUseage() {
        if (isMember()) {
            return false;
        } else if (isEnemy()) {
            return Config.CLAIMS_ENEMY_DENYUSAGE.getOption();
        } else if (isAlly()) {
            return Config.CLAIMS_ALLY_DENYUSAGE.getOption();
        } else if (isTruce()) {
            return Config.CLAIMS_TRUCE_DENYUSAGE.getOption();
        } else {
            return Config.CLAIMS_DENYUSAGE.getOption();
        }
    }

    public double getRelationCost() {
        if (isEnemy()) {
            return Config.ECON_COST_ENEMY.getDouble();
        } else if (isAlly()) {
            return Config.ECON_COST_ALLY.getDouble();
        } else if (isTruce()) {
            return Config.ECON_COST_TRUCE.getDouble();
        } else {
            return Config.ECON_COST_NEUTRAL.getDouble();
        }
    }

    // Utility method to build items for F Perm GUI
    @Override
    public ItemStack buildItem() {
        final ConfigurationSection RELATION_CONFIG = FactionsPlugin.getInstance().getFileManager().getFperms().getConfig().getConfigurationSection("fperm-gui.relation");

        String displayName = replacePlaceholders(RELATION_CONFIG.getString("placeholder-item.name", ""));
        List<String> lore = new ArrayList<>();

        Material material = XMaterial.matchXMaterial(RELATION_CONFIG.getString("materials." + name().toLowerCase())).get().parseMaterial();
        if (material == null) {
            return null;
        }

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        for (String loreLine : RELATION_CONFIG.getStringList("placeholder-item.lore")) {
            lore.add(replacePlaceholders(loreLine));
        }

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public String replacePlaceholders(String string) {
        string = ChatColor.translateAlternateColorCodes('&', string);

        String permissableName = nicename.substring(0, 1).toUpperCase() + nicename.substring(1);

        string = string.replace("{relation-color}", getColor().toString());
        string = string.replace("{relation}", permissableName);

        return string;
    }
}
