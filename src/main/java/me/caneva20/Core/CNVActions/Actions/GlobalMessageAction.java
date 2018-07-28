package me.caneva20.Core.CNVActions.Actions;

import me.caneva20.Core.CNVActions.Action;
import me.caneva20.Core.CNVActions.Paramenters.ActionString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GlobalMessageAction extends Action {
    private static final String MESSAGE = "MESSAGE";

    public GlobalMessageAction() {
        super("GLOBAL_MESSAGE");

        addParameter(MESSAGE, new ActionString(true));
    }

    @Override
    protected void process() {
        String message = get(MESSAGE);

        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    protected boolean validate() {
        return !"".equals(get(MESSAGE));
    }

    @Override
    protected void getValidation() {
        if ("".equals(get(MESSAGE))) {
            validation(MESSAGE, false, "Message not specified");
        }
    }
}



























