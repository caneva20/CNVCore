package me.caneva20.Core.Commands;

import me.caneva20.Core.Commander.CommandBase;
import me.caneva20.Core.Commander.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class InfoCommand extends CommandBase implements ICommand {
    private String command = "";
    private String permission = "cnv.core.info";
    private String usage = "/cnv";
    private List<String> alias = new ArrayList<>();
    private boolean onlyPlayers = false;
    private String description = "Shows the plugin info";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public boolean getOnlyPlayers() {
        return onlyPlayers;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public List<String> getAlias() {
        return alias;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, JavaPlugin plugin) {
        PluginDescriptionFile pdf = plugin.getDescription();
        sendMessageInfo(sender, "Core v" + pdf.getVersion(), true);
        sendMessageInfo(sender, "By: " + pdf.getAuthors(), true);
    }
}
