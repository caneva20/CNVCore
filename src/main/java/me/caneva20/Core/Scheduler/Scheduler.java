package me.caneva20.Core.Scheduler;

import me.caneva20.Core.Generics.Actions.A0.Action;
import me.caneva20.Core.Tasks.Tasks;
import me.caneva20.Core.Util.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final JavaPlugin plugin;
    private Time[] times;
    private Action task;

    public Scheduler(JavaPlugin plugin, long runDelay, Action task) {
        this(plugin, runDelay, task, new Time[0]);
    }

    public Scheduler(JavaPlugin plugin, long runDelay, Action task, String[] times) {
        this.plugin = plugin;
        this.task = task;

        List<Time> timeList = new ArrayList<>();

        for (String time : times) {
            timeList.add(new Time(time));
        }

        this.times = timeList.toArray(new Time[0]);

        start(runDelay);
    }

    public Scheduler(JavaPlugin plugin, long runDelay, Action task, Time[] times) {
        this.plugin = plugin;
        this.task = task;
        this.times = times;

        start(runDelay);
    }

    private void start (final long runDelay) {
        Tasks.runLater(runDelay, () -> {
            for (Time time : times) {
                if (time.canExecute(Util.convertFromTicksToMinutes(runDelay))) {
                    time.execute();
                    task.run();
                }
            }

            resetTimes();
        });
    }

    private void resetTimes () {
        boolean allExecuted = true;

        for (Time time : times) {
            if (!time.isExecuted()) {
                allExecuted = false;

                break;
            }
        }

        if (allExecuted) {
            for (Time time : times) {
                time.reset();
            }
        }
    }

    //Public methods
    public Time getTime(int hour, int minute) {
        for (Time time : times) {
            if (time.getHour() == hour && time.getMinute() == minute) {
                return time;
            }
        }

        return null;
    }

    public Time getTime(String time) {
        Time cnvTime = Util.convertToCNVTime(time);
        if (cnvTime != null) {
            getTime(cnvTime.getHour(), cnvTime.getMinute());
        }

        return null;
    }
}