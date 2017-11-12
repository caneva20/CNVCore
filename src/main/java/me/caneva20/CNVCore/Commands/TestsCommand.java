package me.caneva20.CNVCore.Commands;

import me.caneva20.CNVCore.CNVActions.ActionProcessor;
import me.caneva20.CNVCore.CNVCore;
import me.caneva20.CNVCore.CNVMenus.Menu;
import me.caneva20.CNVCore.CNVMenus.MenuBuilder;
import me.caneva20.CNVCore.Command.CommandBase;
import me.caneva20.CNVCore.Command.ICommand;
import me.caneva20.CNVCore.Util.KeyValuePair;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class TestsCommand extends CommandBase implements ICommand {
    private String command = "test";
    private String permission = "cnv.core.test";
    private String usage = "/cnv test";
    private List<String> alias = new ArrayList<>();
    private boolean onlyPlayers = false;
    private String description = "";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public boolean getOnlyPlayers() {
        return onlyPlayers;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public List<String> getAlias() {
        return alias;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, JavaPlugin plugin) {
        Menu menu = MenuBuilder.get(plugin).createMenu("MainMenu", 1);

        menu.addButton(2, () -> {
            CNVCore.getMainLogger().info(sender,"Hi, you've triggered an button!");
        });

        menu.addButton(6, () -> {
            String action = "ITEM:[ID:264;AMOUNT:{{RANDOM(32,64)}};NAME:Here, take some DIAMONDS!!]";

            ActionProcessor.get().processAction(action, new KeyValuePair<>("PLAYER", sender));

            CNVCore.getMainLogger().info(sender, "Enjoy some DIAMONDS!!");

            if (menu.isHighlighted(6)) {
                menu.unhighlightButton(6);
            } else {
                menu.highlightButton(6);
            }
        });
        
        menu.open(((Player) sender));
    }
}























