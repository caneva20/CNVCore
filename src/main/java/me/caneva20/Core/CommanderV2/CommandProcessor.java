package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterProcessor;
import me.caneva20.Core.Logger.Logger;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CommandProcessor {
    private static Logger logger;

    public static void process(ICommand command, CommandSender sender, Logger logger, List<String> arguments, JavaPlugin plugin) {
        CommandProcessor.logger = logger;
        arguments.remove(0);//The first argument is the command name being processed right now, it should now be removed to not be processed again

        if (!checkPermission(command, sender)) {
            return;
        }

        if (!checkOnlyPlayer(command, sender)) {
            return;
        }

        if (!checkOnlyConsole(command, sender)) {
            return;
        }

        if (command instanceof SubCommander) {
            ((SubCommander)command).run(sender, arguments, plugin);
        } else {
            Arguments argument = ParameterProcessor.process(sender, command, arguments);

            if (argument == null) {
                logger.error(sender, Strings.yourCommandCantBeExecuted());
                logger.info(sender, command.getUsage());
                return;
            }

            command.run(sender, argument, plugin);
        }
    }

    private static boolean checkPermission(ICommand command, CommandSender sender) {
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

    private static boolean checkOnlyPlayer(ICommand command, CommandSender sender) {
        boolean isPlayer = sender instanceof Player;

        if (!isPlayer && command.isOnlyPlayer()) {
            logger.error(sender, "This command can only be executed by players");
            return false;
        }

        return true;
    }

    private static boolean checkOnlyConsole(ICommand command, CommandSender sender) {
        boolean isPlayer = sender instanceof Player;

        if (isPlayer && command.isOnlyConsole()) {
            logger.error(sender, "This command can only be executed through the console");
            return false;
        }

        return true;
    }
}
