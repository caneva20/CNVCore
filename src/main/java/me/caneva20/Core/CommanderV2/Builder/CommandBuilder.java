package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.*;
import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.Core;
import me.caneva20.Core.Generics.Actions.A3.Action;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
class CommandBuilder {
    private Action<CommandSender, Arguments, JavaPlugin> runAction;
    private String commandName;
    private String permission;
    private String description;
    private boolean onlyPlayer;
    private boolean onlyConsole;
    private List<String> aliases = new ArrayList<>();
    private List<IParameter> parameters = new ArrayList<>();

    private boolean autoPermission = true;
    private List<ICommand> subCommands = new ArrayList<>();

    CommandBuilder(String commandName) {
        this.commandName = commandName;
    }

    CommandBuilder() {}

    public CommandBuilder name(String name) {
        if (commandName != null) {
            Core.logger().warnConsole(Strings.commandAlreadyHasNameDefined(commandName, name));
        }

        commandName = name;

        return this;
    }

    public CommandBuilder description(String description) {
        this.description = description;

        return this;
    }

    private String makePermission(JavaPlugin plugin) {
        //TODO:
        return String.format("%s.%s", plugin.getName().toLowerCase(), commandName);
    }

    private String makeUsage(Commander commander) {
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            IParameter par = parameters.get(i);
            //TODO: Get required and optional formats from config <> and []
            params.append(par.isRequired() ? String.format("<%s>", par.getType()) : String
                    .format("&7[%s]", par.getType()));

            if (i != parameters.size() - 1) {
                params.append(" ");
            }
        }

        // /cmd foo <required> [optional]
        return String.format("/<par>%s %s %s</par>", commander.getName(), commandName, params);
    }


    public CommandBuilder runAction(Action<CommandSender, Arguments, JavaPlugin> runAction) {
        this.runAction = runAction;

        return this;
    }

    public CommandBuilder setOnlyForPlayers() {
        if (onlyConsole) {
            Core.logger().warnConsole(Strings.commandAlreadySetToConsole(commandName));
        } else {
            onlyPlayer = true;
        }

        return this;
    }

    public CommandBuilder setOnlyForConsole() {
        if (onlyPlayer) {
            Core.logger().warnConsole(Strings.commandAlreadySetToPlayer(commandName));
        } else {
            onlyConsole = true;
        }

        return this;
    }

    public CommandBuilder addAlias(String alias) {
        aliases.add(alias);

        return this;
    }

    public CommandBuilder setNoPermission() {
        autoPermission = false;

        permission = null;

        return this;
    }

    public CommandBuilder addParameter(IParameter parameter) {
        if (parameter.isRequired() && parameters.size() > 0 && !parameters.get(parameters.size() - 1).isRequired()) {
            Core.logger().errorConsole(Strings.parameterCantBeAddedCauseHasOptional(parameter, commandName));

            return this;
        }

        parameters.add(parameter);

        Core.logger().debug(Strings.parameterAddedToCommand(parameter, commandName));

        return this;
    }

    public CommandBuilder addSubCommand(ICommand subCommand) {
        subCommands.add(subCommand);

        return this;
    }

    public ICommand build(JavaPlugin plugin, Commander commander) {
        if (runAction == null) {
            Core.logger().errorConsole(Strings.noRunActionDefined(commandName));
            return null;
        }

        if (StringUtils.isBlank(description)) {
            Core.logger().warnConsole(Strings.noDescriptionGiven(commandName));
        }

        if (autoPermission) {
            permission = makePermission(plugin);
        }

        String usage = makeUsage(commander);
        IParameter[] parameters = this.parameters.toArray(new IParameter[0]);

        if (subCommands.size() > 0) {
            return new SubCommander(commandName, permission, description, onlyPlayer, onlyConsole, usage, parameters, commander, aliases, subCommands);
        } else {
            return new EmptyCommand(commandName, permission, description, onlyPlayer, onlyConsole, usage, parameters, runAction, commander, aliases);
        }
    }
}
