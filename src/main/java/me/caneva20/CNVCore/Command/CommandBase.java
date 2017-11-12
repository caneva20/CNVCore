package me.caneva20.CNVCore.Command;

import me.caneva20.CNVCore.CNVCore;
import me.caneva20.CNVCore.CNVLogger;
import me.caneva20.CNVCore.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

@SuppressWarnings({"FieldCanBeLocal", "SameParameterValue", "unused"})
public abstract class CommandBase {
    private CNVLogger logger;
    private CNVCore plugin;

    public void registerLogger(CNVLogger logger) {
        this.logger = logger;
    }

    private boolean hasLogger() {
        if (logger != null) {
            return true;
        } else {
            plugin = (CNVCore) Bukkit.getPluginManager().getPlugin("CNVCore");
            CNVCore.getMainLogger().warnConsole("No logger was set to CommandBase! No messages will be shown");
        }

        return false;
    }

    public void sendMessageInfo(CommandSender to, String[] message) {
        sendMessageInfo(to, message, false);
    }

    public void sendMessageInfo(CommandSender to, String[] message, boolean useTag) {
        if (!hasLogger()) {
            return;
        }

        for (String string : message) {
            sendMessageInfo(to, string, useTag);
        }
    }

    public void sendMessageWarn(CommandSender to, String[] message) {
        sendMessageWarn(to, message, false);
    }

    public void sendMessageWarn(CommandSender to, String[] message, boolean useTag) {
        if (!hasLogger()) {
            return;
        }

        for (String string : message) {
            sendMessageWarn(to, string, useTag);
        }
    }

    public void sendMessageInfo(CommandSender to, String message) {
        sendMessageInfo(to, message, false);
    }

    public void sendMessageInfo(CommandSender to, String message, boolean useTag) {
        if (!hasLogger()) {
            return;
        }

        if (to instanceof ConsoleCommandSender) {
            logger.infoConsole(message);
        } else {
            logger.info(to, message, useTag);
        }
    }

    public void sendMessageWarn(CommandSender to, String message) {
        sendMessageWarn(to, message, false);
    }

    public void sendMessageWarn(CommandSender to, String message, boolean useTag) {
        if (!hasLogger()) {
            return;
        }

        if (to instanceof ConsoleCommandSender) {
            logger.warnConsole(message);
        } else {
            logger.warn(to, message, useTag);
        }
    }

    public void sendUsage(CommandSender to, String usage) {
        sendUsage(to, usage, false);
    }

    public void sendUsage(CommandSender to, String usage, boolean useTag) {
        if (!hasLogger()) {
            return;
        }

        sendMessageWarn(to, "Incorrect use.", useTag);
        sendMessageInfo(to, "Try: &6" + Util.formatUsage(usage) + "&e.", useTag);
    }
}






















