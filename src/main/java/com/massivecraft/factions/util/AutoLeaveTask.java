package com.massivecraft.factions.util;

import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.zcore.config.Config;

public class AutoLeaveTask implements Runnable {

    private static AutoLeaveProcessTask task;
    double rate;

    public AutoLeaveTask() {
        this.rate = Config.AUTOSAVE_INTERVAL_MINUTES.getInt();
    }

    public synchronized void run() {
        if (task != null && !task.isFinished()) {
            return;
        }

        task = new AutoLeaveProcessTask();
        task.runTaskTimer(FactionsPlugin.getInstance(), 1, 1);

        // maybe setting has been changed? if so, restart this task at new rate
        if (this.rate != Config.AUTOSAVE_INTERVAL_MINUTES.getInt()) {
            FactionsPlugin.getInstance().startAutoLeaveTask(true);
        }
    }
}
