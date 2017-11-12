package me.caneva20.CNVCore;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class Configuration {
    private CNVCore plugin;
    private CNVConfig config;

    public Configuration(CNVCore plugin) {
        this.plugin = plugin;

        config = new CNVConfig(plugin, "Config", "Config");
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
