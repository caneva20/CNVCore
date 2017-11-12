package me.caneva20.CNVCore;

import me.caneva20.CNVCore.CNVMenus.InventoryListener;
import me.caneva20.CNVCore.Command.CNVCommands;
import me.caneva20.CNVCore.Commands.InfoCommand;
import me.caneva20.CNVCore.Commands.TestsCommand;
import me.caneva20.CNVCore.Events.ItemDespawnEventHandler;
import me.caneva20.CNVCore.Events.ItemPackedEventHandler;
import me.caneva20.CNVCore.Listeners.ItemDespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class CNVCore extends JavaPlugin {
    private static CNVCore instance;
    private static CNVLogger logger;
    private static Vault vault;

    private CNVCommands commands;
    private Configuration configuration;
    private ItemDespawnEventHandler itemDespawnEventHandler;

    @Override
    public void onLoad () {
        logger = new CNVLogger(this);
    }

    public void onEnable() {
        logger.setLoggerTag("&f[&5CNV&6Core&f] ");

        logger.infoConsole("&6|]=---------------------------------------------------------=[|");
        logger.infoConsole("&6|&a		Initializing setup.");
        logger.infoConsole("");
        logger.infoConsole("");

        instance = this;

        //Initialize things here
        vault = new Vault(this);
        commands = new CNVCommands(this, logger);
        configuration = new Configuration(this);

        setupCommands();
        setupListeners();
        setupHandlers();

        logger.infoConsole("");
        logger.infoConsole("");
        logger.infoConsole("&6|		&aEnabled");
        logger.infoConsole("&6|]=---------------------------------------------------------=[|");
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelAllTasks();

        if (itemDespawnEventHandler != null) {
            itemDespawnEventHandler.disable();
        }

        logger.infoConsole("&5|]=---------------------------------=[|");
        logger.infoConsole("&5|]             &6Disabled              &5[|");
        logger.infoConsole("&5|]=---------------------------------=[|");
    }

    private void setupCommands () {
        logger.infoConsole("Registering commands");

        getCommand("cnv").setExecutor(commands);
        commands.registerCommand(new InfoCommand());
        commands.registerCommand(new TestsCommand());

        logger.infoConsole("All commands registered!");
    }

    private void setupListeners () {
        getServer().getPluginManager().registerEvents(new ItemDespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemPackedEventHandler(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    private void setupHandlers () {
        if (configuration.getItemDespawnEventEnabled()) {
            itemDespawnEventHandler = new ItemDespawnEventHandler(this, configuration.getItemDespawnEventRunDelay());
        } else if (configuration.getItemDespawnEventWarningEnabled()) {
            logger.errorConsole("<par>CUSTOM_EVENTS.ITEM_DESPAWN_EVENT</par> is disabled!");
        }
    }

    public static CNVLogger getMainLogger () {
        return logger;
    }

    public static Vault getVault () {
        return vault;
    }

    public static CNVCore get() {
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
