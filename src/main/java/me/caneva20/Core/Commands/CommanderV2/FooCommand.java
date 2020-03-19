package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Builder.BuilderCommand;
import me.caneva20.Core.CommanderV2.BuiltIn.Commands.HelpCommand;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FooCommand extends BuilderCommand {
    public void build() {
        name("foo");
        onlyConsole();
        description("FOO!");

        //addSubCommand(BarCommand.class)
        //addSubCommand(BarCommand.class)
        //addSubCommand(BarCommand.class)

        addSubCommand(BarCommand.class);
        addSubCommand(BarCommand.class);
        addSubCommand(HiCommand.class);
    }

    public void run(CommandSender sender, Arguments args, JavaPlugin plugin) {
        Core.logger().debug("FOOOOOOOO!");
    }
}
