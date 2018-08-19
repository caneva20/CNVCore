package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.Util.CollectionUtil;

import java.util.List;

public abstract class BaseCommand implements ICommand {
    private String name;
    private String permission;
    private String description;
    private boolean onlyPlayer;
    private boolean onlyConsole;
    private List<String> aliases;
    private IParameter[] parameters;
    private Commander commander;
    private String usage;

    public BaseCommand(String name, String permission, String description, boolean onlyPlayer, boolean onlyConsole, String usage, IParameter[] parameters, Commander commander, List<String> aliases) {
        this.name = name.toLowerCase();
        this.permission = permission == null ? "" : permission.toLowerCase();
        this.description = description;
        this.onlyPlayer = onlyPlayer;
        this.onlyConsole = onlyConsole;
        this.usage = usage;
        this.aliases = CollectionUtil.select(aliases, String::toLowerCase);
        this.parameters = parameters;
        this.commander = commander;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOnlyPlayer() {
        return onlyPlayer;
    }

    @Override
    public boolean isOnlyConsole() {
        return onlyConsole;
    }

    @Override
    public IParameter[] getParameters() {
        return parameters;
    }

    @Override
    public Commander getCommander() {
        return commander;
    }

    @Override
    public String getUsage() {
        return usage;
    }
}
