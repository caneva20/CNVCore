package me.caneva20.Core.Commands.CommanderV2;

import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Builder.BuilderCommand;
import me.caneva20.Core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BarCommand extends BuilderCommand {
    public void build() {
        name("bar");
        onlyPlayers();
    }

    public void run(CommandSender sender, Arguments args, JavaPlugin plugin) {
        Core.logger().info(sender, "Hi!");
    }
}
