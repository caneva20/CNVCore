package me.caneva20.CNVCore;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"SameParameterValue", "ResultOfMethodCallIgnored", "unused"})
public class CNVConfig {
    private final JavaPlugin plugin;
    private final CNVLogger logger;
    private String fileName;
    private String jarFileName;

    private FileConfiguration customConfig;
    private File customConfigFile;

    public CNVConfig(JavaPlugin plugin, String fileName, String jarFileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.logger = new CNVLogger(plugin);
        this.jarFileName = jarFileName;

        saveDefaultConfig();
    }

    public CNVConfig(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, fileName);
    }

    public void reloadCustomConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), fileName + ".yml");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(jarFileName + ".yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            customConfig.setDefaults(defConfig);
        }
    }

    //Just return the customConfig
    public FileConfiguration getCustomConfig() {
        if (customConfig == null) {
            reloadCustomConfig();
        }

        return customConfig;
    }

    //Save to a file, write on disk.
    public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }

        try {
            getCustomConfig().save(customConfigFile);
        } catch (IOException ex) {
            logger.warnConsole("Could not save config to " + customConfigFile);
            logger.warnConsole("With exception: " + ex);
        }
    }

    //Get the default customConfig inside the .jar and write it to hard disk
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), fileName + ".yml");
        }

        if (!customConfigFile.exists()) {
            plugin.saveResource(jarFileName + ".yml", false);
            File file = new File(plugin.getDataFolder(), jarFileName + ".yml");

            String[] folderPath = fileName.split("/");
            StringBuilder folderPath2 = new StringBuilder();

            for (int i = 0; i < folderPath.length - 1; i++) {
                folderPath2.append(folderPath[i]).append("/");
            }

            File folder = new File(plugin.getDataFolder(), folderPath2.toString());
            folder.mkdirs();

            fileName = folderPath[folderPath.length - 1];
            file.renameTo(new File(plugin.getDataFolder(), folderPath2 + fileName + ".yml"));

        }
    }

    //Wrappers
    public void set (String path, Object value) {
        getCustomConfig().set(path, value);
        saveCustomConfig();
    }

    public Object get (String path) {
        return getCustomConfig().get(path);
    }

    public Object get (String path, Object defaultValue) {
        return getCustomConfig().get(path, defaultValue);
    }

    public String getString (String path) {
        return getCustomConfig().getString(path);
    }

    public String getString (String path, String defaultValue) {
        return getCustomConfig().getString(path, defaultValue);
    }

    public List<String> getStringList (String path) {
        return getCustomConfig().getStringList(path);
    }

    public int getInt (String path) {
        return getCustomConfig().getInt(path);
    }

    public int getInt (String path, int defaultValue) {
        return getCustomConfig().getInt(path, defaultValue);
    }

    public List<Integer> getIntList (String path) {
        return getCustomConfig().getIntegerList(path);
    }

    public double getDouble (String path) {
        return getCustomConfig().getDouble(path);
    }

    public double getDouble (String path, double defaultValue) {
        return getCustomConfig().getDouble(path, defaultValue);
    }

    public List<Double> getDoubleList (String path) {
        return getCustomConfig().getDoubleList(path);
    }

    public boolean getBoolean (String path) {
        return getCustomConfig().getBoolean(path);
    }

    public boolean getBoolean (String path, boolean defaultValue) {
        return getCustomConfig().getBoolean(path, defaultValue);
    }

    public List<Boolean> getBooleanList (String path) {
        return getCustomConfig().getBooleanList(path);
    }

    public ItemStack getItemStack(String path) {
        return getCustomConfig().getItemStack(path);
    }

    public ItemStack getItemStack(String path, ItemStack defaultValue) {
        return getCustomConfig().getItemStack(path, defaultValue);
    }

    public long getLong(String path) {
        return getCustomConfig().getLong(path);
    }

    public long getLong(String path, long defaultValue) {
        return getCustomConfig().getLong(path, defaultValue);
    }

    public List<Long> getLongList(String path) {
        return getCustomConfig().getLongList(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return getCustomConfig().getConfigurationSection(path);
    }

    public ArrayList<String> getSection(String key) {
        if (getCustomConfig().contains(key)) {
            ArrayList<String> section = new ArrayList<>();
            String[] keys = getConfigurationSection(key).getKeys(false).toArray(new String[0]);
            section.addAll(Arrays.asList(keys));
            return section;
        }
        return null;
    }

    public String getCurrentPath() {
        return getCustomConfig().getCurrentPath();
    }

    public Configuration getDefaults () {
        return getCustomConfig().getDefaults();
    }

    public Set<String> getKeys(boolean deep) {
        return getCustomConfig().getKeys(deep);
    }

    public boolean contains (String path) {
        return getCustomConfig().contains(path);
    }
}













