package me.caneva20.Core.Logger;

import me.caneva20.Core.Logger.Tags.LoggerTags;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Logger {
    private final JavaPlugin plugin;
    private String tag;
    private boolean tagByDefault;

    public Logger(JavaPlugin plugin, String tag, boolean tagByDefault) {
        this.plugin = plugin;
        this.tag = tag == null ? "&f[&6" + plugin.getName() + "&f] " : tag;
        this.tagByDefault = tagByDefault;
    }

    public Logger(JavaPlugin plugin) {
        this(plugin, null, true);
    }

    public Logger(JavaPlugin plugin, boolean tagByDefault) {
        this(plugin, null, tagByDefault);
    }

    public Logger(JavaPlugin plugin, String tag) {
        this(plugin, tag, false);
    }

    private String format(String rawMessage, MessageLevel level) {
        rawMessage = LoggerTags.process(rawMessage, level);

        return ChatColor.translateAlternateColorCodes('&', rawMessage);
    }

    public void sendMessage(CommandSender to, String message, boolean useTag, MessageLevel level) {
        if (!LoggerConfig.isLevelEnabled(level)) {
            return;
        }

        message = format((useTag ? tag : "") + "<begin>" + message, level);

        to.sendMessage(message);
    }

    public void setLoggerTag(String tag) {
        debug("Logger tag of <par>" + plugin.getName() + "</par> changed from \'<par>" + this.tag + "</par>\' to \'<par>" + tag + "</par>\'");
        this.tag = tag;
    }

    public void log(CommandSender sender, String message) {
        sender.sendMessage(message);
    }


    public void infoConsole(String message) {
        info(plugin.getServer().getConsoleSender(), message, true);
    }

    public void warnConsole(String message) {
        warn(plugin.getServer().getConsoleSender(), message, true);
    }

    public void successConsole(String message) {
        success(plugin.getServer().getConsoleSender(), message, true);
    }

    public void errorConsole(String message) {
        error(plugin.getServer().getConsoleSender(), message, true);
    }


    public void info(CommandSender to, String message) {
        info(to, message, tagByDefault);
    }

    public void info(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.INFO);
    }

    public void warn(CommandSender to, String message) {
        warn(to, message, tagByDefault);
    }

    public void warn(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.WARNING);
    }

    public void success(CommandSender to, String message) {
        success(to, message, tagByDefault);
    }

    public void success(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.SUCCESS);
    }

    public void error(CommandSender to, String message) {
        error(to, message, tagByDefault);
    }

    public void error(CommandSender to, String message, boolean useTag) {
        sendMessage(to, message, useTag, MessageLevel.ERROR);
    }


    public void debug(String message) {
        message = format(tag + "&f[&bDEBUG&f]&b " + message, MessageLevel.DEBUG);

        plugin.getServer().getConsoleSender().sendMessage(message);
    }

    public void debug(String[] string) {
        for (String s : string) {
            debug(s);
        }
    }
}
