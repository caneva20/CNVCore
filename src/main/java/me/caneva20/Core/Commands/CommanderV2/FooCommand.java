package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Builder.BaseCommandBuilder;
import me.caneva20.Core.CommanderV2.BuiltIn.Parameters.BuiltInParameters;
import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;
import me.caneva20.Core.Core;
import me.caneva20.Core.Util.Random;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class FooCommand extends BaseCommandBuilder {
    private final String[] greetings = new String[] {"Hi", "Hey", "Hello", "Olá"};

    private ParameterAccessor<Player> player;
    private ParameterAccessor<Integer> amount;

    public void build() {
        name("foo");
        onlyConsole();
        description("FOO!");

        //TODO: Add accessors

//        parameter(BuiltInParameters.String); //Required
        amount = parameter(BuiltInParameters.Int).getAccessor();
//        floatPar = parameter(BuiltInParameters.Float).optional().getAccessor();
//        parameter(BuiltInParameters.Player).optional().name("Bar");

        player = parameter(BuiltInParameters.Player).optional().getAccessor();
    }

    public void run(CommandSender sender, CommandArgument args, JavaPlugin plugin) {
        for (Integer i = 0; i < amount.get(); i++) {
            java.util.Random rand = Random.getRandom();
            rand.setSeed(System.nanoTime() * i * i);

            int index = (int) (rand.nextFloat() * greetings.length);

            Core.logger().info(sender, String.format("%s %s", greetings[index], sender.getName()));
        }

    }
}
