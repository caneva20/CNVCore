package me.caneva20.Core.Tasks;

import me.caneva20.Core.Generics.Actions.A0.Action;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Tasks {
    private static JavaPlugin plugin;

    private static JavaPlugin getPlugin() {
        if (plugin == null) {
            plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Core");
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
