package me.caneva20.CNVCore.Command;

import me.caneva20.CNVCore.CNVLogger;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@SuppressWarnings("unused")
public interface ICommand {

    String getCommand();

    String getPermission();

    boolean getOnlyPlayers();

    String getUsage();

    List<String> getAlias();

    String getDescription();

    void onCommand(CommandSender sender, String[] args, JavaPlugin plugin);

    void registerLogger(CNVLogger logger);
}
