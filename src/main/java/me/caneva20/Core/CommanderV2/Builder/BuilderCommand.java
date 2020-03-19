package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ICommand;
import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class BuilderCommand implements IBuilderCommand {
    private CommandBuilder commandBuilder;
    private List<ParameterBuilder> parameters = new ArrayList<>();
    private List<ParameterAccessor<?>> accessors = new ArrayList<>();
    private List<Class<? extends BuilderCommand>> subCommandBuilders = new ArrayList<>();

    public BuilderCommand() {
        commandBuilder = new CommandBuilder();
    }

    private void runAccessors(CommandSender sender, Arguments args, JavaPlugin plugin) {
        for (ParameterAccessor<?> accessor : accessors) {
            accessor.process(args);
        }

        try {
            run(sender, args, plugin);
        } catch (Exception ignored) {
        }

        for (ParameterAccessor<?> accessor : accessors) {
            accessor.dispose();
        }
    }

    void registerAccessor(ParameterAccessor<?> accessor) {
        accessors.add(accessor);
    }

    protected void name(String name) {
        commandBuilder.name(name);
    }

    protected void description(String description) {
        commandBuilder.description(description);
    }

    protected void onlyPlayers() {
        commandBuilder.setOnlyForPlayers();
    }

    protected void onlyConsole() {
        commandBuilder.setOnlyForConsole();
    }

    protected void alias(String alias) {
        commandBuilder.addAlias(alias);
    }

    protected void noPermission() {
        commandBuilder.setNoPermission();
    }

    protected void permissionless() {
        commandBuilder.setNoPermission();
    }

    protected <T1, T2 extends IParameter<T1>> ParameterBuilder<T1> parameter(Class<T2> type) {
        try {
            IParameter<T1> parameter = type.newInstance();

            ParameterBuilder<T1> builder = new ParameterBuilder<>(parameter, this);

            parameters.add(builder);

            return builder;
        } catch (Exception e) {
            Core.logger().errorConsole(String.format("Parameter %s could not be instantiated", type.getName()));

            throw new NoClassDefFoundError(String.format("Parameter %s could not be instantiated", type.getName()));
        }
    }

    protected void addSubCommand(Class<? extends BuilderCommand> command) {
        subCommandBuilders.add(command);
    }

    protected abstract void run(CommandSender sender, Arguments args, JavaPlugin plugin);

    @Override
    public ICommand build(JavaPlugin plugin, Commander commander) {
        build();

        for (Class<? extends BuilderCommand> subCommandBuilder : subCommandBuilders) {
            try {
                ICommand subCommand = subCommandBuilder.newInstance().build(plugin, commander);

                commandBuilder.addSubCommand(subCommand);

                Core.logger().debug(String.format("SubCommand <par>%s</par> was added", subCommand.getName()));
            } catch (Exception e) {
                Core.logger().debug("Could not create sub command");

                e.printStackTrace();
            }
        }

        commandBuilder.runAction(this::runAccessors);

        for (ParameterBuilder parameterBuilder : parameters) {
            IParameter parameter = parameterBuilder.build();

            commandBuilder.addParameter(parameter);
        }

        return commandBuilder.build(plugin, commander);
    }

    public abstract void build();
}
