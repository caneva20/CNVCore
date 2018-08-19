package me.caneva20.Core.CNVActions.Actions;

import me.caneva20.Core.CNVActions.Action;
import me.caneva20.Core.CNVActions.Paramenters.ActionPlayer;
import me.caneva20.Core.CNVActions.Paramenters.ActionString;
import me.caneva20.Core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandAction extends Action {
    private static final String NAME = "NAME";
    private static final String METHOD = "METHOD";
    private static final String PLAYER = "PLAYER";

    public CommandAction() {
        super("COMMAND");

        addParameter(NAME, new ActionString(true));
        addParameter(METHOD, new ActionString(true));
        addParameter(PLAYER, new ActionPlayer(true));
    }

    @Override
    protected void process() {
        Player player = get(PLAYER);
        String command = get(NAME);
        String method = ((String) get(METHOD)).toUpperCase();

        if (player == null) {
            Core.logger().errorConsole("INVALID ACTION");
            Core.logger().errorConsole("Player not found");

            return;
        }

        command = command.replace("{{PLAYER_NAME}}", player.getName());

        switch (method) {
            case "OP":
                if (player.isOp()) {
                    Bukkit.getServer().dispatchCommand(player, command);
                    break;
                }

                player.setOp(true);

                Bukkit.getServer().dispatchCommand(player, command);

                player.setOp(false);
                break;
            case "PLAYER":
                Bukkit.getServer().dispatchCommand(player, command);
                break;
            case "CONSOLE":
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);

                break;
            default:
                Core.logger().errorConsole("A command could no be executed because <par>" + method + "</par> is not a valid method");
                Core.logger().errorConsole("Options are:");
                Core.logger().errorConsole("  - OP: Execute as OP");
                Core.logger().errorConsole("  - PLAYER: Execute as the player itself");
                Core.logger().errorConsole("  - CONSOLE: Execute as the console");
                break;
        }
    }

    @Override
    protected boolean validate() {
        String method = ((String) get(METHOD)).toUpperCase();

        boolean invalidMethod = !"OP".equals(method) && !"PLAYER".equals(method) && !"CONSOLE".equals(method);

        return get(PLAYER) != null && !"".equals(get(NAME)) && !invalidMethod;
    }

    @Override
    protected void getValidation() {
        if (get(PLAYER) == null) {
            validation(PLAYER, false, "Player is null");
        }

        if ("".equals(get(NAME))) {
            validation(NAME, false, "No command passed");
        }

        String method = ((String) get(METHOD)).toUpperCase();

        boolean invalidMethod = !"OP".equals(method) && !"PLAYER".equals(method) && !"CONSOLE".equals(method);

        if (invalidMethod) {
            validation(METHOD, false, "Method <par>" + method + "</par> is not valid. Options are: <par>OP</par>, <par>PLAYER</par> or <par>CONSOLE</par>");
        }
    }
}




















