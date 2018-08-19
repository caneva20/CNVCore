package me.caneva20.Core.CommanderV2.ParameterProcessor;

import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.CommanderV2.ICommand;
import me.caneva20.Core.Util.CollectionUtil;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandParameterProcessor {
    public static CommandArgument process(CommandSender sender, ICommand command, List<String> args) {
        IParameter[] parameters = command.getParameters();
        int min = getMinLength(parameters);
        int max = getMaxLength(parameters);

        if (!checkLength(min, max, args)) {
            String message = String.format("Sorry, but your command can't be processed because it requires <par>%s</par>" +
                    " to <par>%s</par> parameters and you gave <par>%s</par>", min, max, args.size());

            command.getCommander().getLogger().error(sender, message);

            return null;
        }

        CommandArgument commandArgument = new CommandArgument(parameters, command);
        boolean hasFails = false;

        for (int i = 0; i < args.size(); i++) {
            IParameter parameter = parameters[i];
            String arg = args.get(i);

            //Failed
            if (!parameter.process(arg)) {
                command.getCommander().getLogger().error(sender, parameter.getErrorMessage(arg));
                hasFails = true;
            }

            commandArgument.addProcessed(parameter);
        }

        if (hasFails) {
            return null;
        }

        return commandArgument;
    }

    private static int getMinLength(IParameter[] parameters) {
        return CollectionUtil.count(parameters, IParameter::isRequired);
    }

    private static int getMaxLength(IParameter[] parameters) {
        return parameters.length;
    }

    private static boolean checkLength(int min, int max, List<String> args) {
        return args.size() >= min && args.size() <= max;
    }
}
