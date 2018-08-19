package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ICommand;
import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommandBuilder implements ICommandBuilder {
    private BuilderCommand builderCommand;
    private List<ParameterBuilder> parameters = new ArrayList<>();
    private List<ParameterAccessor<?>> accessors = new ArrayList<>();

    public BaseCommandBuilder() {
        builderCommand = new BuilderCommand();
    }

    protected void name(String name) {
        builderCommand.name(name);
    }

    protected void description(String description) {
        builderCommand.description(description);
    }

    protected void onlyPlayers() {
        builderCommand.setOnlyForPlayers();
    }

    protected void onlyConsole() {
        builderCommand.setOnlyForConsole();
    }

    protected void alias(String alias) {
        builderCommand.addAlias(alias);
    }

    protected void noPermission() {
        builderCommand.setNoPermission();
    }

    protected void permissionless() {
        builderCommand.setNoPermission();
    }

    protected ParameterBuilder parameter(Class<? extends IParameter> type) {
        try {
            IParameter parameter = type.newInstance();

            ParameterBuilder builder = new ParameterBuilder(parameter, this);

            parameters.add(builder);

            return builder;
        } catch (Exception e) {
            Core.logger().errorConsole(String.format("Parameter %s could not be instantiated", type.getName()));

            throw new NoClassDefFoundError(String.format("Parameter %s could not be instantiated", type.getName()));
        }
    }

    @Override
    public ICommand build(JavaPlugin plugin, Commander commander) {
        build();
        builderCommand.runAction(this::runAccessors);

        for (ParameterBuilder parameterBuilder : parameters) {
            IParameter parameter = parameterBuilder.build();

            builderCommand.addParameter(parameter);
        }

        return builderCommand.build(plugin, commander);
    }

    void registerAccessor(ParameterAccessor<?> accessor) {
        accessors.add(accessor);
    }

    private void runAccessors(CommandSender sender, CommandArgument args, JavaPlugin plugin) {
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

    public abstract void build();

    protected abstract void run(CommandSender sender, CommandArgument args, JavaPlugin plugin);
}
