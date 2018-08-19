package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Builder.BaseCommandBuilder;
import me.caneva20.Core.CommanderV2.BuiltIn.Parameters.StringParameter;
import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

///<> -> Required
///[] -> Optional
///cmd hi <required> [optional]
///cmd hi <required> <required> [optional]

///cmd hi <player>
///cmd hi <offline_player>
///cmd hi <int>
///cmd hi <float>
///cmd hi <number>
///cmd hi <string>
///cmd hi <*>

/*
*
* addParam<Integer>().optional();
* addParam<Player>().required();
* addParam<String>().name("foo");
* addParam<?>() = <*>;
* addParam();
*
* */

public class HiCommand extends BaseCommandBuilder {
    public void build() {
        name("hi");
        onlyConsole(); //Intended
        onlyPlayers(); //Intended
        alias("hello");
        alias("hey");
        alias("ola");
        alias("hola");
    }

    protected void run(CommandSender sender, CommandArgument args, JavaPlugin plugin) {
        Core.logger().info(sender, "Hi!");
    }
}
