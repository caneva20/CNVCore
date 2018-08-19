package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.BaseCommand;
import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.Generics.Actions.A3.Action;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class EmptyCommand extends BaseCommand {
    private final Action<CommandSender, CommandArgument, JavaPlugin> runAction;

    public EmptyCommand(String name, String permission, String description, boolean onlyPlayer, boolean onlyConsole, String usage, IParameter[] parameters, Action<CommandSender, CommandArgument, JavaPlugin> runAction, Commander commander, List<String> aliases) {
        super(name, permission, description, onlyPlayer, onlyConsole, usage, parameters, commander, aliases);
        this.runAction = runAction;
    }

    @Override
    public void run(CommandSender sender, CommandArgument args, JavaPlugin plugin) {
        if (runAction == null) {
            return;
        }

        runAction.run(sender, args, plugin);
    }
}
