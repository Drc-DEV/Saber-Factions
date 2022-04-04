package com.massivecraft.factions.cmd;

import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.CC;
import com.massivecraft.factions.zcore.config.Config;
import com.massivecraft.factions.zcore.util.TL;

public class CmdSpawnerLock extends FCommand {

    /**
     * @author Illyria Team
     */

    public CmdSpawnerLock() {
        super();
        this.aliases.addAll(Aliases.spawnerlock);

        this.requirements = new CommandRequirements.Builder(Permission.LOCKSPAWNERS)
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        Config.FACTION_SPAWNER_LOCK.setOption(!Config.FACTION_SPAWNER_LOCK.getOption());
        context.msg(TL.COMMAND_SPAWNER_LOCK_TOGGLED, Config.FACTION_SPAWNER_LOCK.getOption() ? CC.translate("&aEnabled") : CC.translate("&4Disabled"));
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_SPAWNER_LOCK_DESCRIPTION;
    }
}
