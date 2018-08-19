package me.caneva20.Core.Commander;

import me.caneva20.Core.Logger.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface ICommand {

    String getCommand();

    String getPermission();

    boolean getOnlyPlayers();

    String getUsage();

    List<String> getAlias();

    String getDescription();

    void onCommand(CommandSender sender, String[] args, JavaPlugin plugin);

    void registerLogger(Logger logger);
}
