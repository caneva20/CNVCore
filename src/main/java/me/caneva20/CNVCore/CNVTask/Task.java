package me.caneva20.CNVCore.CNVTask;

import me.caneva20.CNVCore.Generics.A0.Action;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Task {
    private static JavaPlugin plugin;

    private static JavaPlugin getPlugin() {
        if (plugin == null) {
            plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("CNVCore");
        }

        return plugin;
    }

    public static void runLater (long delay, Action action) {
        new BukkitRunnable() {
            @Override
            public void run() {
                action.run();
            }
        }.runTaskLater(getPlugin(), delay);
    }

    public static void runLaterAsync (long delay, Action action) {
        new BukkitRunnable() {
            @Override
            public void run() {
                action.run();
            }
        }.runTaskLaterAsynchronously(getPlugin(), delay);
    }
}
