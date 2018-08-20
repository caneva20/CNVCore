package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Builder.CommandBuilder;
import me.caneva20.Core.CommanderV2.BuiltIn.Parameters.BuiltInParameters;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;
import me.caneva20.Core.Core;
import me.caneva20.Core.Util.Random;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

public class HiCommand extends CommandBuilder {
    private final String[] greetings = new String[] {"Hi", "Hey", "Hello", "Olá"};

    private ParameterAccessor<Player> player;

    public void build() {
        name("hi");
        description("Says hi to someone or to yourself");
        onlyConsole(); //Intended
        onlyPlayers(); //Intended
        alias("hello");
        alias("hey");
        alias("ola");
        alias("hola");

        player = parameter(BuiltInParameters.Player).optional().getAccessor();
    }

    protected void run(CommandSender sender, Arguments args, JavaPlugin plugin) {
        String greeting = greetings[Random.range(0, greetings.length)];

        if (player.sent()) {
            Core.logger().info(player.get(), String.format("%s said %s to you", sender.getName(), greeting));
        } else {
            Core.logger().info(sender, String.format("%s %s", greeting, sender.getName()));
        }
    }
}














