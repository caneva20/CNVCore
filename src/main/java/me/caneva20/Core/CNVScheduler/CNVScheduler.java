package me.caneva20.Core.CNVScheduler;

import me.caneva20.Core.Util.Util;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ALL", "SameReturnValue"})
public class CNVScheduler {
    private final JavaPlugin plugin;
    private CNVTime[] times;
    private CNVTask task;

    public CNVScheduler(JavaPlugin plugin, long runDelay, CNVTask task) {
        this(plugin, runDelay, task, new CNVTime[0]);
    }

    public CNVScheduler(JavaPlugin plugin, long runDelay, CNVTask task, String[] times) {
        this.plugin = plugin;
        this.task = task;

        List<CNVTime> timeList = new ArrayList<>();

        for (String time : times) {
            timeList.add(new CNVTime(time));
        }

        this.times = timeList.toArray(new CNVTime[0]);

        start(runDelay);
    }

    public CNVScheduler(JavaPlugin plugin, long runDelay, CNVTask task, CNVTime[] times) {
        this.plugin = plugin;
        this.task = task;
        this.times = times;

        start(runDelay);
    }

    private void start (final long runDelay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (CNVTime time : times) {
                    if (time.canExecute(Util.convertFromTicksToMinutes(runDelay))) {
                        time.execute();
                        task.run();
                    }
                }

                resetTimes();
            }
        }.runTaskTimer(plugin, 0L, runDelay);
    }

    private void resetTimes () {
        boolean allExecuted = true;

        for (CNVTime time : times) {
            if (!time.isExecuted()) {
                allExecuted = false;

                break;
            }
        }

        if (allExecuted) {
            for (CNVTime time : times) {
                time.reset();
            }
        }
    }

    //Public methods
    public CNVTime getTime (int hour, int minute) {
        for (CNVTime time : times) {
            if (time.getHour() == hour && time.getMinute() == minute) {
                return time;
            }
        }

        return null;
    }

    public CNVTime getTime (String time) {
        CNVTime cnvTime = Util.convertToCNVTime(time);
        if (cnvTime != null) {
            getTime(cnvTime.getHour(), cnvTime.getMinute());
        }

        return null;
    }
}






















