package me.caneva20.CNVCore.CNVActions.Actions;

import me.caneva20.CNVCore.CNVActions.Action;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionPlayer;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionString;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageAction extends Action {
    private static final String PLAYER = "PLAYER";
    private static final String MESSAGE = "MESSAGE";

    public MessageAction() {
        super(MESSAGE);

        addParameter(PLAYER, new ActionPlayer(true));
        addParameter(MESSAGE, new ActionString(true));
    }

    @Override
    protected void process() {
        Player player = get(PLAYER);
        String message = get(MESSAGE);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    protected boolean validate() {
        return !"".equals(get(MESSAGE)) && get(PLAYER) != null;
    }

    @Override
    protected void getValidation() {
        if (get(PLAYER) == null) {
            validation(PLAYER, false, "Player is null");
        }

        if ("".equals(get(MESSAGE))) {
            validation(MESSAGE, false, "Message not specified");
        }
    }
}
