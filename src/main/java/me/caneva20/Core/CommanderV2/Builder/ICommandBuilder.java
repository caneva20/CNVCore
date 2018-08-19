package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.Commander;
import me.caneva20.Core.CommanderV2.ICommand;
import org.bukkit.plugin.java.JavaPlugin;

public interface ICommandBuilder {
    ICommand build(JavaPlugin plugin, Commander commander);
}
