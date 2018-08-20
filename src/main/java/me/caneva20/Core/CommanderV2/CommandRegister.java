package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.Builder.ICommandBuilder;
import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ICommand;
import me.caneva20.Core.Core;

public class CommandRegister {
    public static void register(Commander commander, Class<? extends ICommandBuilder> clazz) {
        try {
            ICommand command = clazz.newInstance().build(commander.getPlugin(), commander);

            commander.registerCommand(command);

            //TODO: Remove this from here
//            commander.getLogger().debug(String.format("Command <par>%s</par> registered for <par>%s</par> with permission node of <par>%s</par>",
//                    command.getName(), commander.getName(), command.getPermission()));
        } catch (Exception e) {
            Core.logger().errorConsole(String.format("Could not register command of type <par>%s</par>", clazz.getName()));

            e.printStackTrace();
        }
    }
}
