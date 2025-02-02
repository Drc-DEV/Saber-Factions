package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.util.TL;


public class CmdOwnerList extends FCommand {

    /**
     * @author FactionsUUID Team - Modified By CmdrKittens
     */

    public CmdOwnerList() {
        super();
        this.aliases.addAll(Aliases.owner_list);

        this.requirements = new CommandRequirements.Builder(Permission.OWNERLIST)
                .playerOnly()
                .memberOnly()
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        boolean hasBypass = context.fPlayer.isAdminBypassing();

        if (!hasBypass && !context.assertHasFaction()) {
            return;
        }

        if (!Config.OWNED_CHUNKS_ENABLED.getOption()) {
            context.msg(TL.COMMAND_OWNERLIST_DISABLED);
            return;
        }

        FLocation flocation = new FLocation(context.fPlayer);

        if (Board.getInstance().getFactionAt(flocation) != context.faction) {
            if (!hasBypass) {
                context.msg(TL.COMMAND_OWNERLIST_WRONGFACTION);
                return;
            }
            //TODO: This code won't ever be called.
            context.faction = Board.getInstance().getFactionAt(flocation);
            if (!context.faction.isNormal()) {
                context.msg(TL.COMMAND_OWNERLIST_NOTCLAIMED);
                return;
            }
        }

        String owners = context.faction.getOwnerListString(flocation);

        if (owners == null || owners.isEmpty()) {
            context.msg(TL.COMMAND_OWNERLIST_NONE);
            return;
        }

        context.msg(TL.COMMAND_OWNERLIST_OWNERS, owners);
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_OWNERLIST_DESCRIPTION;
    }
}