package me.caneva20.CNVCore.CNVActions.Actions;

import me.caneva20.CNVCore.CNVActions.Action;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionDouble;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionPlayer;
import me.caneva20.CNVCore.CNVCore;
import org.bukkit.entity.Player;

public class MoneyAction extends Action {
    private static final String PLAYER = "PLAYER";
    private static final String AMOUNT = "AMOUNT";

    public MoneyAction () {
        super("MONEY");

        addParameter(PLAYER, new ActionPlayer(true));
        addParameter(AMOUNT, new ActionDouble(true));
    }

    @Override
    protected void process() {
        Player player = get(PLAYER);
        double amount = get(AMOUNT);

        CNVCore.getVault().depositPlayer(player, amount);
    }

    @Override
    protected boolean validate() {
        return get(PLAYER) != null;
    }

    @Override
    protected void getValidation() {
        if (get(PLAYER) == null) {
            validation(PLAYER, false, "Player is null");
        }
    }
}
