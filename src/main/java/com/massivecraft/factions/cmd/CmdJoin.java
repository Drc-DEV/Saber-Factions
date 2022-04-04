package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.Logger;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.frame.fupgrades.UpgradeType;
import com.massivecraft.factions.zcore.util.TL;
import org.bukkit.Bukkit;

public class CmdJoin extends FCommand {

    public CmdJoin() {
        super();
        this.aliases.addAll(Aliases.join);
        this.requiredArgs.add("faction name");
        this.optionalArgs.put("player", "you");

        this.requirements = new CommandRequirements.Builder(Permission.JOIN)
                .playerOnly()
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        FactionsPlugin.getInstance().getServer().getScheduler().runTaskAsynchronously(FactionsPlugin.instance, () -> {


            Faction faction = context.argAsFaction(0);
            if (faction == null) {
                return;
            }

            FPlayer fplayer = context.argAsBestFPlayerMatch(1, context.fPlayer, false);
            boolean samePlayer = fplayer == context.fPlayer;

            if (!samePlayer && !Permission.JOIN_OTHERS.has(context.sender, false)) {
                context.msg(TL.COMMAND_JOIN_CANNOTFORCE);
                return;
            }

            if (!faction.isNormal()) {
                context.msg(TL.COMMAND_JOIN_SYSTEMFACTION);
                return;
            }

            if (faction == fplayer.getFaction()) {
                context.msg(TL.COMMAND_JOIN_ALREADYMEMBER, fplayer.describeTo(context.fPlayer, true), (samePlayer ? "are" : "is"), faction.getTag(context.fPlayer));
                return;
            }

            if (!faction.altInvited(fplayer) && Config.FACTION_MEMBER_LIMIT.getInt() > 0 && faction.getFPlayers().size() >= getFactionMemberLimit(faction)) {
                context.msg(TL.COMMAND_JOIN_ATLIMIT_MEMBERS, faction.getTag(context.fPlayer), getFactionMemberLimit(faction), fplayer.describeTo(context.fPlayer, false));
                return;
            }

            if (fplayer.hasFaction()) {
                context.msg(TL.COMMAND_JOIN_INOTHERFACTION, fplayer.describeTo(context.fPlayer, true), (samePlayer ? "your" : "their"));
                return;
            }

            if (!Config.FACTION_NEGATIVEPOWER_LEAVE.getOption() && fplayer.getPower() < 0) {
                context.msg(TL.COMMAND_JOIN_NEGATIVEPOWER, fplayer.describeTo(context.fPlayer, true));
                return;
            }

            if (!(faction.getOpen() || faction.isInvited(fplayer) || context.fPlayer.isAdminBypassing() || Permission.JOIN_ANY.has(context.sender, false))) {
                context.msg(TL.COMMAND_JOIN_REQUIRESINVITATION);
                if (samePlayer) {
                    faction.msg(TL.COMMAND_JOIN_ATTEMPTEDJOIN, fplayer.describeTo(faction, true));
                }
                return;
            }

            int altLimit = Config.FACTION_ALT_LIMIT.getInt();

            if (altLimit > 0 && faction.getAltPlayers().size() >= altLimit && faction.altInvited(context.fPlayer)) {
                context.msg(TL.COMMAND_JOIN_ATLIMIT_ALTS, faction.getTag(context.fPlayer), altLimit, fplayer.describeTo(context.fPlayer, false));
                return;
            }

            // if economy is enabled, they're not on the bypass list, and this command has a cost set, make sure they can pay
            if (samePlayer && !context.canAffordCommand(Config.ECON_COST_JOIN.getDouble(), TL.COMMAND_JOIN_TOJOIN.toString())) {
                return;
            }

            // Check for ban
            if (!context.fPlayer.isAdminBypassing() && faction.isBanned(context.fPlayer)) {
                context.msg(TL.COMMAND_JOIN_BANNED, faction.getTag(context.fPlayer));
                return;
            }

            // Cannot asynchronously call events
            FactionsPlugin.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(FactionsPlugin.getInstance(), () -> {
                // trigger the join event (cancellable)
                FPlayerJoinEvent joinEvent = new FPlayerJoinEvent(FPlayers.getInstance().getByPlayer(context.player), faction, FPlayerJoinEvent.PlayerJoinReason.COMMAND);
                Bukkit.getServer().getPluginManager().callEvent(joinEvent);
                if (joinEvent.isCancelled()) {
                    return;
                }

                // then make 'em pay (if applicable)
                if (samePlayer && !context.payForCommand(Config.ECON_COST_JOIN.getDouble(), TL.COMMAND_JOIN_TOJOIN.toString(), TL.COMMAND_JOIN_FORJOIN.toString())) {
                    return;
                }

                context.msg(TL.COMMAND_JOIN_SUCCESS, fplayer.describeTo(context.fPlayer, true), faction.getTag(context.fPlayer));

                if (!samePlayer) {
                    fplayer.msg(TL.COMMAND_JOIN_MOVED, context.fPlayer.describeTo(fplayer, true), faction.getTag(fplayer));
                }

                faction.msg(TL.COMMAND_JOIN_JOINED, fplayer.describeTo(faction, true));

                fplayer.resetFactionData();

                if (faction.altInvited(fplayer)) {
                    fplayer.setAlt(true);
                    fplayer.setFaction(faction, true);
                } else {
                    fplayer.setFaction(faction, false);
                }

                faction.deinvite(fplayer);
                context.fPlayer.setRole(faction.getDefaultRole());

                if (Config.LOG_FJOIN.getOption()) {
                    if (samePlayer) {
                        Logger.printArgs(TL.COMMAND_JOIN_JOINEDLOG.toString(), Logger.PrefixType.DEFAULT, fplayer.getName(), faction.getTag());
                    } else {
                        Logger.printArgs(TL.COMMAND_JOIN_MOVEDLOG.toString(), Logger.PrefixType.DEFAULT, context.fPlayer.getName(), fplayer.getName(), faction.getTag());
                    }
                }
            });
        });
    }

    private int getFactionMemberLimit(Faction f) {
        if (f.getUpgrade(UpgradeType.MEMBERS) == 0) return Config.FACTION_MEMBER_LIMIT.getInt();
        return Config.FACTION_MEMBER_LIMIT.getInt() + FactionsPlugin.getInstance().getFileManager().getUpgrades().getConfig().getInt("fupgrades.MainMenu.Members.Members-Limit.level-" + f.getUpgrade(UpgradeType.MEMBERS));
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_JOIN_DESCRIPTION;
    }
}

