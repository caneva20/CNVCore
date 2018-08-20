package me.caneva20.Core.CommanderV2;

import javafx.util.Pair;
import me.caneva20.Core.CommanderV2.BuiltIn.Commands.HelpCommand;
import me.caneva20.Core.Core;
import me.caneva20.Core.Logger.Logger;
import me.caneva20.Core.Util.CollectionUtil;
import me.caneva20.Core.Util.Util;
import me.caneva20.Core.Vault;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public
class Commander implements CommandExecutor {
//    private Set<ICommand> commands = new HashSet<>();
    private Map<String, ICommand> commandMap = new HashMap<>();
    private Map<String, ICommand> aliasMap = new HashMap<>();
    private Logger logger;
    private JavaPlugin plugin;
    private String name;

    private Vault vault;

    public Commander(JavaPlugin plugin, String name, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;
        this.name = name;

        PluginCommand command = plugin.getCommand(name);

        if (command == null) {
            logger.errorConsole(Strings.commandNotFoundInPluginYml(name));

            return;
        }

        logger.debug(Strings.commandRegistered(name));

        command.setExecutor(this);
        vault = new Vault(plugin);

        registerBuiltIns(plugin);
    }

    public Commander(JavaPlugin plugin, String name) {
        this(plugin, name, new Logger(plugin));
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Collection<ICommand> getCommands() {
        return commandMap.values();
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    private void registerBuiltIns(JavaPlugin plugin) {
        registerCommand(new HelpCommand(this).build(plugin, this));
    }

    public void registerCommand(ICommand command) {
        if (command == null) {
            logger.errorConsole(Strings.pluginTriedToRegisterNullCommand(plugin));
            return;
        }

        commandMap.put(Util.sanitize(command.getName()), command);

        for (String alias : command.getAliases()) {
            aliasMap.put(Util.sanitize(alias), command);
        }

        vault.registerPermission(command.getPermission());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
        long nanoTime = System.nanoTime();

        args = fixArgs(args);
        ArrayList<String> arguments = CollectionUtil.asList(args);
        arguments.remove(0);//Removes the command from the list

        Pair<Boolean, ICommand> checkCommand = checkCommand(args, sender);

        if (!checkCommand.getKey()) {
            return false;
        }

        ICommand command = checkCommand.getValue();

        CommandProcessor.process(command, sender, logger, arguments, plugin);

        Core.logger().debug(Strings.commandTookToExecute(command, System.nanoTime() - nanoTime, true));

        return false;
    }

    private String[] fixArgs(String[] args) {
        args = CollectionUtil.select(args, String::toLowerCase).toArray(new String[0]);

        if (hasAnyCommand(args)) {
            return args;
        }

        return new String[]{""};
    }

    private String getCommandName(String[] args) {
        return args[0];
    }

    private ICommand findCommand(String name) {
        if (commandMap.containsKey(name)) {
            return commandMap.get(name);
        }

        if (aliasMap.containsKey(name)) {
            return aliasMap.get(name);
        }

        return null;
    }

    //Checks
    private Pair<Boolean, ICommand> checkCommand(String[] args, CommandSender sender) {
        if (!hasAnyCommand(args)) {
            logger.error(sender, Strings.invalidCommand());

            return new Pair<>(false, null);
        }

        String name = getCommandName(args);
        ICommand command = findCommand(name);

        if (command == null) {
            logger.error(sender, String.format("Command <par>%s</par> not found", name));
            logger.error(sender, Strings.commandNotFound(name));

            return new Pair<>(false, null);
        }

        return new Pair<>(true, command);
    }

    private boolean hasAnyCommand(String[] args) {
        return args != null && args.length > 0;
    }
}
