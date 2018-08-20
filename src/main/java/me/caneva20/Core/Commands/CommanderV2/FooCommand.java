package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Builder.CommandBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FooCommand extends CommandBuilder {
    public void build() {
        name("foo");
        onlyConsole();
        description("FOO!");

        //addCommand(BarCommand.class)
        //addCommand(BarCommand.class)
        //addCommand(BarCommand.class)
    }

    public void run(CommandSender sender, Arguments args, JavaPlugin plugin) {

    }
}
