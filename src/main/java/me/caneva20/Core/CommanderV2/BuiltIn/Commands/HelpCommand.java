package me.caneva20.Core.CommanderV2.BuiltIn.Commands;

import me.caneva20.Core.CommanderV2.Builder.BuilderCommand;
import me.caneva20.Core.CommanderV2.Arguments;
import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ICommand;
import me.caneva20.Core.Core;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpCommand extends BuilderCommand {
    private final Commander commander;

    public HelpCommand(Commander commander) {

        this.commander = commander;
    }

    @Override
    public void build() {
        name("help");
        alias("?");
        alias("");

        description("Show the help");
    }

    @Override
    protected void run(CommandSender sender, Arguments args, JavaPlugin plugin) {
        for (ICommand command : commander.getCommands()) {
            String description = StringUtils.isBlank(command.getDescription())
                    ? "NO DESCRIPTION PROVIDED"
                    : command.getDescription();

            String message = String.format("%s: %s", command.getUsage(), description);
            Core.logger().info(sender, message);

            //Chat command API
//            TextComponent message = new TextComponent();
//
//            String aliases = StringUtils.join(command.getAliases(), "\n");
//            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(aliases)});
//
//            TextComponent commandName = new TextComponent(String.format("<par>%s</par>", command.getType()));
//            commandName.setHoverEvent(hoverEvent);
//
//            message.addExtra(commandName);
//            message.addExtra(new TextComponent(String.format(": %s", command.getDescription())));
//
//            ((Player)sender).spigot().sendMessage(message);
        }
    }
}
