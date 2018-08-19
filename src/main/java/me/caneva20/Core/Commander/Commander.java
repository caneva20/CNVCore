package me.caneva20.Core.Commander;

import me.caneva20.Core.Logger.Logger;
import me.caneva20.Core.Vault;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commander extends CommandBase implements CommandExecutor {
    private List<ICommand> commands = new ArrayList<>();

    private final JavaPlugin plugin;
    private final Vault vault;
    private final Logger logger;

    public Commander(JavaPlugin plugin, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;

        registerLogger(logger);

        vault = new Vault(plugin);
    }

    public Commander(JavaPlugin plugin, Logger logger, String name) {
        this.plugin = plugin;
        this.logger = logger;

        registerLogger(logger);

        vault = new Vault(plugin);

        plugin.getCommand(name).setExecutor(this);
    }

    public void registerCommand(ICommand command) {
        command.registerLogger(logger);

        commands.add(command);

        vault.registerPermission(command.getPermission());
    }

    public java.util.List<ICommand> getCommands() {
        return this.commands;
    }

    public boolean onCommand(CommandSender sender, Command c, String arg2, String[] args) {
        if (args.length <= 0) {
            args = new String[]{""};
        }

        ICommand command = null;
        ICommand rootCommand = null;

        for (ICommand cmd : commands) {
            if (cmd.getCommand().equalsIgnoreCase(args[0])) {
                command = cmd;

                for (String alias : cmd.getAlias()) {
                    if (alias.equalsIgnoreCase(args[0])) {
                        command = cmd;
                        break;
                    }
                }
                break;
            }

            // Checks to see if this command is a root command and has parameters
            if (cmd.getCommand().matches(" \\[par]")) {
                rootCommand = cmd;
            }
        }

        if (command == null) {
            // Checks to see if there's a 'root' command and it has parameters
            // If it is not done root commands with params won't be called because the first param
            // is being understood as a command not as a param
            if (rootCommand != null) {
                command = rootCommand;
            } else {
                logger.error(sender, "The command <par>" + args[0] + "</par> doesn't exist");
                return true;
            }
        } else {
            rootCommand = null;
        }

        if (!(sender instanceof Player) && (command.getOnlyPlayers())) {
            logger.error(sender, "This command can ONLY be executed by players");
            return true;
        }

        if (!sender.hasPermission(command.getPermission())) {
            logger.error(sender, "You don't have permission to use this command. <par>" + command.getCommand() + "</par>");
            return true;
        }

        // We can't remove the first param if the command is a root command, because the first param is actually a param
        if (rootCommand == null || args[0].equals("")) {
            ArrayList<String> newArgs = new ArrayList<>();
            Collections.addAll(newArgs, args);
            newArgs.remove(0);
            args = newArgs.toArray(new String[0]);
        }

        command.onCommand(sender, args, plugin);
        return true;
    }
}