package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.util.TL;

public class CmdDelFWarp extends FCommand {

    /**
     * @author FactionsUUID Team - Modified By CmdrKittens
     */

    public CmdDelFWarp() {
        super();
        this.aliases.addAll(Aliases.deletefwarp);
        this.requiredArgs.add("warp name");

        this.requirements = new CommandRequirements.Builder(Permission.SETWARP)
                .playerOnly()
                .memberOnly()
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        String warp = context.argAsString(0);
        if (context.faction.isWarp(warp)) {
            if (!transact(context.fPlayer, context)) {
                return;
            }
            context.faction.removeWarp(warp);
            context.msg(TL.COMMAND_DELFWARP_DELETED, warp);
        } else {
            context.msg(TL.COMMAND_DELFWARP_INVALID, warp);
        }
    }

    private boolean transact(FPlayer player, CommandContext context) {
        return Config.ECON_COST_DELWARP.getDouble() <= 0 || player.isAdminBypassing() || context.payForCommand(Config.ECON_COST_DELWARP.getDouble(), TL.COMMAND_DELFWARP_TODELETE.toString(), TL.COMMAND_DELFWARP_FORDELETE.toString());
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_DELFWARP_DESCRIPTION;
    }
}

