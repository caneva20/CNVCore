package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Builder.BaseCommandBuilder;
import me.caneva20.Core.CommanderV2.CommandArgument;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BarCommand extends BaseCommandBuilder {
    public void build() {
        name("bar");
        onlyPlayers();
    }

    public void run(CommandSender sender, CommandArgument args, JavaPlugin plugin) {
        Core.logger().info(sender, "Hi!");
    }
}
