package me.caneva20.Core.CNVActions.Paramenters;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionPlayer extends ActionParameter {
    private Player defaultValue;

    public ActionPlayer() {
    }

    public ActionPlayer(boolean isRequired) {
        super(isRequired);
    }

    public ActionPlayer(boolean isRequired, Player defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionPlayer(Player defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Player getValue(String in) {
        return Bukkit.getServer().getPlayerExact(in);
    }

    @Override
    public Player getDefaultValue() {
        return defaultValue;
    }
}
