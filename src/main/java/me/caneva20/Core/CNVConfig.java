package me.caneva20.Core;

import me.caneva20.Core.Logger.Logger;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CNVConfig {
    private final JavaPlugin plugin;
    private final Logger logger;
    private String fileName;

    private FileConfiguration configuration;
    private File file;

    public CNVConfig(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.logger = new Logger(plugin);

        createFile();
    }

    public void reload() {
        file = new File(plugin.getDataFolder(), fileName + ".yml");

        configuration = YamlConfiguration.loadConfiguration(file);

        createFile();
    }

    public FileConfiguration get() {
        if (configuration == null) {
            reload();
        }

        return configuration;
    }

    public void save() {
        if (configuration == null || file == null) {
            return;
        }

        try {
            get().save(file);
        } catch (IOException ex) {
            logger.warnConsole("Could not save config to " + file);
            logger.warnConsole("With exception: " + ex);
        }
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName + ".yml", false);
        }
    }

    //Wrappers
    public void set(String path, Object value) {
        get().set(path, value);
        save();
    }

    public Object get(String path) {
        return get().get(path);
    }

    public Object get(String path, Object defaultValue) {
        return get().get(path, defaultValue);
    }

    public String getString(String path) {
        return get().getString(path);
    }

    public String getString(String path, String defaultValue) {
        return get().getString(path, defaultValue);
    }

    public List<String> getStringList(String path) {
        return get().getStringList(path);
    }

    public int getInt(String path) {
        return get().getInt(path);
    }

    public int getInt(String path, int defaultValue) {
        return get().getInt(path, defaultValue);
    }

    public List<Integer> getIntList(String path) {
        return get().getIntegerList(path);
    }

    public double getDouble(String path) {
        return get().getDouble(path);
    }

    public double getDouble(String path, double defaultValue) {
        return get().getDouble(path, defaultValue);
    }

    public List<Double> getDoubleList(String path) {
        return get().getDoubleList(path);
    }

    public boolean getBoolean(String path) {
        return get().getBoolean(path);
    }

    public boolean getBoolean(String path, boolean defaultValue) {
        return get().getBoolean(path, defaultValue);
    }

    public List<Boolean> getBooleanList(String path) {
        return get().getBooleanList(path);
    }

    public ItemStack getItemStack(String path) {
        return get().getItemStack(path);
    }

    public ItemStack getItemStack(String path, ItemStack defaultValue) {
        return get().getItemStack(path, defaultValue);
    }

    public long getLong(String path) {
        return get().getLong(path);
    }

    public long getLong(String path, long defaultValue) {
        return get().getLong(path, defaultValue);
    }

    public List<Long> getLongList(String path) {
        return get().getLongList(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return get().getConfigurationSection(path);
    }

    public ArrayList<String> getSection(String key) {
        if (get().contains(key)) {
            String[] keys = getConfigurationSection(key).getKeys(false).toArray(new String[0]);
            return new ArrayList<>(Arrays.asList(keys));
        }

        return null;
    }

    public String getCurrentPath() {
        return get().getCurrentPath();
    }

    public Configuration getDefaults() {
        return get().getDefaults();
    }

    public Set<String> getKeys(boolean deep) {
        return get().getKeys(deep);
    }

    public boolean contains(String path) {
        return get().contains(path);
    }
}