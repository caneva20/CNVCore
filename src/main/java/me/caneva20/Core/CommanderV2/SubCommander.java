package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.Core;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SubCommander extends Command {
    private List<ICommand> commands;

    public SubCommander(String name, String permission, String description, boolean onlyPlayer, boolean onlyConsole, String usage, IParameter[] parameters, Commander commander, List<String> aliases, List<ICommand> commands) {
        super(name, permission, description, onlyPlayer, onlyConsole, usage, parameters, commander, aliases);
        this.commands = commands;
    }

    public void run(CommandSender sender, List<String> args, JavaPlugin plugin) {
        String join = StringUtils.join(args, ", ");

        Core.logger().debug(String.format("Args: [<par>%s</par>]", join));

        int i = 1;
        for (ICommand command : commands) {
            //TODO: ...
            Core.logger().debug(String.format("#%s Command <par>%s</par>", i++, command.getName()));
        }
    }

    @Override
    public void run(CommandSender sender, Arguments args, JavaPlugin plugin) {}

    //cmd [foo set]
    //cmd [foo set foo] <integer>
    //cmd [foo set bar] <player>
    //cmd [foo set bazz] <string>
    //cmd [foo set bizz] <player>


    //cmd [foo toggle]
    //cmd [foo toggle foo]
    //cmd [foo toggle bar]
    //cmd [foo toggle bizz] <integer> [optional]



    // /cmd foo [set|toggle|--]

    //cmd
}
