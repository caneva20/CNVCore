package me.caneva20.Core;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class Configuration {
    private Core plugin;
    private CNVConfig config;

    public Configuration(Core plugin) {
        this.plugin = plugin;

        config = new CNVConfig(plugin, "Config");
    }

    public boolean getItemDespawnEventEnabled () {
        return config.getBoolean("CUSTOM_EVENTS.ITEM_DESPAWN_EVENT.ENABLED", true);
    }

    public long getItemDespawnEventRunDelay () {
        return config.getLong("CUSTOM_EVENTS.ITEM_DESPAWN_EVENT.RUN_DELAY", 1200L);
    }

    public boolean getItemDespawnEventWarningEnabled () {
        return !config.getBoolean("CUSTOM_EVENTS.ITEM_DESPAWN_EVENT.DISABLE_WARNING", false);
    }

}
