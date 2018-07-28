package me.caneva20.Core.Listeners;

import me.caneva20.Core.Core;
import org.bukkit.event.Listener;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ItemDespawnListener implements Listener {
    private Core plugin;

    public ItemDespawnListener(Core plugin) {
        this.plugin = plugin;
    }
}
