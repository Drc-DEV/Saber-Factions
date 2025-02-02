package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;
import com.massivecraft.factions.util.Cooldown;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.util.TL;

public class CmdOpen extends FCommand {

    /**
     * @author FactionsUUID Team - Modified By CmdrKittens
     */

    public CmdOpen() {
        super();
        this.aliases.addAll(Aliases.open);
        this.optionalArgs.put("yes/no", "flip");

        this.requirements = new CommandRequirements.Builder(Permission.OPEN)
                .withRole(Role.COLEADER)
                .playerOnly()
                .memberOnly()
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        // if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
        if (!context.payForCommand(Config.ECON_COST_OPEN.getDouble(), TL.COMMAND_OPEN_TOOPEN, TL.COMMAND_OPEN_FOROPEN)) {
            return;
        }

        if (Cooldown.isOnCooldown(context.fPlayer.getPlayer(), "openCooldown") && !context.fPlayer.isAdminBypassing()) {
            context.msg(TL.COMMAND_COOLDOWN);
            return;
        }

        context.faction.setOpen(!context.faction.getOpen());

        String open = context.faction.getOpen() ? TL.COMMAND_OPEN_OPEN.toString() : TL.COMMAND_OPEN_CLOSED.toString();

        // Inform
        for (FPlayer fplayer : FPlayers.getInstance().getOnlinePlayers()) {
            if (fplayer.getFactionId().equals(context.faction.getId())) {
                fplayer.msg(TL.COMMAND_OPEN_CHANGES, context.fPlayer.getName(), open);
                Cooldown.setCooldown(fplayer.getPlayer(), "openCooldown", FactionsPlugin.getInstance().getConfig().getInt("fcooldowns.f-open"));
                continue;
            }
            if (!Config.FACTION_BROADCAST_OPEN.getOption()) return;
            fplayer.msg(TL.COMMAND_OPEN_CHANGED, context.faction.getTag(fplayer.getFaction()), open);
        }
        if (!Config.FACTION_BROADCAST_OPEN.getOption()) {
            for (FPlayer fPlayer : context.faction.getFPlayersWhereOnline(true)) {
                fPlayer.msg(TL.COMMAND_OPEN_CHANGED, context.faction.getTag(fPlayer.getFaction()), open);
            }
        }

    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_OPEN_DESCRIPTION;
    }

}
