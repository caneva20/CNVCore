package me.caneva20.CNVCore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides simple and easy ways to log strings to the console or players. Also support colors from the beginning
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public class CNVLogger {
    private final JavaPlugin plugin;
    private String tag = "&f[&5CNV&6Logger&f] ";
    private boolean tagByDefault = true;
    @SuppressWarnings("CanBeFinal")
    private Map<MessageLevel, char[]> colorsMap = new HashMap<>();

    /**
     * Creates a new logger
     * @param plugin The plugin that owns this logger
     */
    public CNVLogger(JavaPlugin plugin) {
        this.plugin = plugin;

        initColors();
    }

    public CNVLogger(JavaPlugin plugin, boolean tagByDefault) {
        this.plugin = plugin;
        this.tagByDefault = tagByDefault;

        initColors();
    }

    public CNVLogger(JavaPlugin plugin, String tag) {
        this.plugin = plugin;
        this.tag = tag;

        initColors();
    }

    public CNVLogger(JavaPlugin plugin, String tag, boolean tagByDefault) {
        this.plugin = plugin;
        this.tag = tag;
        this.tagByDefault = tagByDefault;

        initColors();
    }

    private void initColors () {
        colorsMap.put(MessageLevel.SUCCESS, new char[]{'2', 'a'});
        colorsMap.put(MessageLevel.ERROR, new char[]{'4', 'c'});
        colorsMap.put(MessageLevel.INFO, new char[]{'3', 'b'});
        colorsMap.put(MessageLevel.WARN, new char[]{'6', 'e'});
        colorsMap.put(MessageLevel.DEBUG, new char[]{'3', 'b'});
    }

    public void changeColor (MessageLevel level, char[] newColors) {
        colorsMap.put(level, newColors);
    }

    /**
     * Defines the tag used on console
     * @param tag The tag that will be used
     */
    public void setLoggerTag (String tag) {
        infoConsole("Logger tag of <par>" + plugin.getName() + "</par> changed from \'<par>" + this.tag + "</par>\' to \'<par>" + tag + "</par>\'");
        this.tag = tag;
    }

    /**
     * Logs something to @sender
     * @param sender Who will receive the message, it also can be the console
     * @param message The message the will be sent
     */
    public void log(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    public void sendMessage (CommandSender to, String message, boolean useTag, MessageLevel level) {
        message = format((useTag ? tag : "") + "<begin>" + message, level);

        to.sendMessage(message);
    }

    /**
     * Logs something to the console, with INFO level
     * @param message The message to be sent
     */
    public void infoConsole(String message) {
        info(plugin.getServer().getConsoleSender(), message, true);
    }

    /**
     * Logs something to the console, with WARN level
     * @param message The message to be sent
     */
    public void warnConsole(String message) {
        warn(plugin.getServer().getConsoleSender(), message, true);
    }

    /**
     * Logs something to the console, with SUCCESS level
     * @param message The message to be sent
     */
    public void successConsole(String message) {
        success(plugin.getServer().getConsoleSender(), message, true);
    }

    /**
     * Logs something to the console, with ERROR level
     * @param message The message to be sent
     */
    public void errorConsole(String message) {
        error(plugin.getServer().getConsoleSender(), message, true);
    }


    /**
     * Sends a message to @to with INFO level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     */
    public void info(CommandSender to, String message) {
        info(to, message, tagByDefault);
    }

    /**
     * Sends a message to @to with INFO level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     * @param useTag Should the tag be used?
     */
    public void info(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.INFO);
    }

    /**
     * Sends a message to @to with WARN level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     */
    public void warn(CommandSender to, String message) {
        warn(to, message, tagByDefault);
    }

    /**
     * Sends a message to @to with WARN level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     * @param useTag Should the tag be used?
     */
    public void warn(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.WARN);
    }

    /**
     * Sends a message to @to with SUCCESS level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     */
    public void success (CommandSender to, String message) {
        success(to, message, tagByDefault);
    }

    /**
     * Sends a message to @to with SUCCESS level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     * @param useTag Should the tag be used?
     */
    public void success (CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.SUCCESS);
    }

    /**
     * Sends a message to @to with ERROR level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     */
    public void error (CommandSender to, String message) {
        error(to, message, tagByDefault);
    }

    /**
     * Sends a message to @to with ERROR level
     *
     * @param to Who will receive this message
     * @param message The message to be sent
     * @param useTag Should the tag be used?
     */
    public void error (CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.ERROR);
    }

    /**
     * Sends a message to the console with [DEBUG] tag with DEBUG level
     * @param message The message to be sent
     */
    public void debug(String message) {
        message = format("&f[&bDEBUG&f]&b " + message, MessageLevel.DEBUG);

        plugin.getServer().getConsoleSender().sendMessage(message);
    }

    /**
     * Sends a message to the console with [DEBUG] tag with DEBUG level
     * @param string The messages to be sent
     */
    public void debug(String[] string) {
        for (String s : string) {
            debug(s);
        }
    }

    public String formatTags (String rawMessage, MessageLevel level) {
        if (colorsMap == null || colorsMap.isEmpty()) {
            return rawMessage;
        }

        return rawMessage
                .replace("<begin>", "&" + colorsMap.get(level)[1])
                .replace("<end>", "&" + colorsMap.get(level)[1])
                .replace("<par>", "&" + colorsMap.get(level)[0])
                .replace("</par>", "&" + colorsMap.get(level)[1]);
    }

    public String format(String rawMessage, MessageLevel level) {
        return ChatColor.translateAlternateColorCodes('&', formatTags(rawMessage, level));
    }
}
