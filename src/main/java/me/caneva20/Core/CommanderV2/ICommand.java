package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public interface ICommand {
    String getName();

    String getPermission();

    boolean isOnlyPlayer();

    boolean isOnlyConsole();

    String getDescription();

    List<String> getAliases();

    IParameter[] getParameters();

    Commander getCommander(); //TODO: Maybe remove this

    String getUsage();

    void run(CommandSender sender, Arguments args, JavaPlugin plugin);
}
