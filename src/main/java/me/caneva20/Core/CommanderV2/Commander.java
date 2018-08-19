package me.caneva20.Core.CommanderV2;

import javafx.util.Pair;
import me.caneva20.Core.CommanderV2.BuiltIn.Commands.HelpCommand;
import me.caneva20.Core.CommanderV2.ParameterProcessor.CommandParameterProcessor;
import me.caneva20.Core.Core;
import me.caneva20.Core.Logger.Logger;
import me.caneva20.Core.Util.CollectionUtil;
import me.caneva20.Core.Vault;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public
class Commander implements CommandExecutor {
    private Set<ICommand> commands = new HashSet<>();
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
            logger.errorConsole(String.format("Command <par>%s</par> was not found in plugin.yml", name));

            return;
        }

        logger.debug(String.format("Command <par>%s</par> registered", name));

        command.setExecutor(this);
        vault = new Vault(plugin);

        registerBuiltIns(plugin);
    }

    private void registerBuiltIns(JavaPlugin plugin) {
        registerCommand(new HelpCommand(this).build(plugin, this));
    }

    public Commander(JavaPlugin plugin, String name) {
        this(plugin, name, new Logger(plugin));
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Set<ICommand> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    public void registerCommand(ICommand command) {
        if (command == null) {
            logger.errorConsole(String.format("<par>%s</par> tried to register a <par>null</par> command", plugin.getName()));
            return;
        }

        commands.add(command);

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

        if (!checkPermission(command, sender)) {
            return false;
        }

        if (!checkOnlyPlayer(command, sender)) {
            return false;
        }

        if (!checkOnlyConsole(command, sender)) {
            return false;
        }

        CommandArgument argument = CommandParameterProcessor.process(sender, command, arguments);

        if (argument == null) {
            logger.error(sender, "Sorry, but your command could not be executed");
            logger.info(sender, command.getUsage());
            return false;
        }

        long totalNs = System.nanoTime() - nanoTime;

        Core.logger().debug(String.format("Command <par>%s</par> took <par>%s</par>ms (<par>%s</par>ns) to execute. "
                + "(Without command.run())", command.getName(), totalNs / 1000000D, totalNs));

        command.run(sender, argument, plugin);

        totalNs = System.nanoTime() - nanoTime;

        Core.logger().debug(String.format("Command <par>%s</par> took <par>%s</par>ms (<par>%s</par>ns) to execute. "
                + "(With command.run())", command.getName(), totalNs / 1000000D, totalNs));

        return false;
    }

    //Checks
    private Pair<Boolean, ICommand> checkCommand(String[] args, CommandSender sender) {
        if (!hasCommand(args)) {
            logger.error(sender, "Invalid command");

            return new Pair<>(false, null);
        }

        String name = getCommandName(args);
        ICommand command = findCommand(name);

        if (command == null) {
            logger.error(sender, String.format("Command <par>%s</par> not found", name));

            return new Pair<>(false, null);
        }

        return new Pair<>(true, command);
    }

    private boolean checkPermission(ICommand command, CommandSender sender) {
        String permission = command.getPermission();

        if (StringUtils.isEmpty(permission)) {
            return true;
        }

        boolean hasPermission = sender.hasPermission(permission);

        if (!hasPermission) {
            logger.error(sender, "Sorry, you don't have permission to run this command");
            return false;
        }

        return true;
    }

    private boolean checkOnlyPlayer(ICommand command, CommandSender sender) {
        boolean isPlayer = sender instanceof Player;

        if (!isPlayer && command.isOnlyPlayer()) {
            logger.error(sender, "This command can only be executed by players");
            return false;
        }

        return true;
    }

    private boolean checkOnlyConsole(ICommand command, CommandSender sender) {
        boolean isPlayer = sender instanceof Player;

        if (isPlayer && command.isOnlyConsole()) {
            logger.error(sender, "This command can only be executed through the console");
            return false;
        }

        return true;
    }


    private String[] fixArgs(String[] args) {
        args = CollectionUtil.select(args, String::toLowerCase).toArray(new String[0]);

        if (hasCommand(args)) {
            return args;
        }

        return new String[]{""};
    }

    private boolean hasCommand(String[] args) {
        return args != null && args.length > 0;
    }

    private String getCommandName(String[] args) {
        if (!hasCommand(args)) {
            return "";
        }

        return args[0];
    }

    private ICommand findCommand(String name) {
        Optional<ICommand> first = commands.stream()
                .filter(x -> x.getName().equals(name) || x.getAliases().contains(name))
                .findFirst();

        return first.orElse(null);
    }
}
