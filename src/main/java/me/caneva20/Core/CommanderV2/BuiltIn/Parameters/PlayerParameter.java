package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.BaseParameter;
import me.caneva20.Core.Core;
import org.bukkit.entity.Player;

public class PlayerParameter extends BaseParameter<Player> {
    public PlayerParameter() {
        super("player");
    }

    @Override
    public boolean process(String input) {
        Player player = Core.get().getServer().getPlayerExact(input);

        if (player != null) {
            setValue(player);
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage(String input) {
        return String.format("The player <par>%s</par> was not found. Is he online?", input);
    }
}
