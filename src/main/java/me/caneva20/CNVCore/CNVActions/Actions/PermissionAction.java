package me.caneva20.CNVCore.CNVActions.Actions;

import me.caneva20.CNVCore.CNVActions.Action;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionPlayer;
import me.caneva20.CNVCore.CNVActions.Paramenters.ActionString;
import me.caneva20.CNVCore.CNVCore;
import org.bukkit.entity.Player;

public class PermissionAction extends Action {
    private static final String PLAYER = "PLAYER";
    private static final String PERMISSION = "PERMISSION";

    public PermissionAction () {
        super(PERMISSION);

        addParameter(PLAYER, new ActionPlayer(true));
        addParameter(PERMISSION, new ActionString(true));
    }

    @Override
    protected void process() {
        Player player = get(PLAYER);
        String perm = get(PERMISSION);

        CNVCore.getVault().addPermission(player, perm);
    }

    @Override
    protected boolean validate() {
        return get(PLAYER) != null && !"".equals(get(PERMISSION));
    }

    @Override
    protected void getValidation() {
        if (get(PLAYER) == null) {
            validation(PLAYER, false, "Player is null");
        }

        if ("".equals(get(PERMISSION))) {
            validation(PERMISSION, false, "Permission not specified");
        }
    }
}
