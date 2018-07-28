package me.caneva20.Core.Util;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused", "unchecked"})
public class Metadata {
    public static void addMetadata (JavaPlugin plugin, Metadatable metadatable, String key, Object value) {
        metadatable.setMetadata(key, new FixedMetadataValue(plugin, value));
    }

    public static boolean hasMetadata (Metadatable metadatable, String key) {
        return metadatable.hasMetadata(key);
    }

    public static <T> T getMetadata (Metadatable metadatable, String key) {
        return (T) metadatable.getMetadata(key).get(0).value();
    }
}
