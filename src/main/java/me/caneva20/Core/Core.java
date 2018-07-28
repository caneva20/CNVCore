package me.caneva20.Core;

import me.caneva20.Core.CNVMenus.InventoryListener;
import me.caneva20.Core.Command.CNVCommands;
import me.caneva20.Core.Commands.InfoCommand;
import me.caneva20.Core.Commands.TestsCommand;
import me.caneva20.Core.Events.ItemDespawnEventHandler;
import me.caneva20.Core.Events.ItemPackedEventHandler;
import me.caneva20.Core.Listeners.ItemDespawnListener;
import me.caneva20.Core.Logger.Logger;
import me.caneva20.Core.Logger.LoggerConfig;
import me.caneva20.Core.Logger.Tags.LoggerTags;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    private static Core instance;
    private static Logger logger;
    private static Vault vault;

    private CNVCommands commands;
    private Configuration configuration;
    private ItemDespawnEventHandler itemDespawnEventHandler;

    public static Logger getMainLogger() {
        return logger;
    }

    public static Vault getVault() {
        return vault;
    }

    public static Core get() {
        return instance;
    }

    @Override
    public void onLoad() {
        logger = new Logger(this);
    }

    public void onEnable() {
        LoggerConfig.setup(this);
        LoggerTags.setup(this);

        logger.setLoggerTag("&f[&5c20&6Core&f] ");

        logger.infoConsole("<par>|]=---------------------------------------------------------=[|</par>");
        logger.infoConsole("<par>|</par>		Initializing setup.");
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
        logger.infoConsole("<par>|</par>		Enabled");
        logger.infoConsole("<par>|]=---------------------------------------------------------=[|</par>");
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelAllTasks();

        if (itemDespawnEventHandler != null) {
            itemDespawnEventHandler.disable();
        }

        logger.infoConsole("<par>|]=---------------------------------=[|</par>");
        logger.infoConsole("<par>|</par>             Disabled                  <par>|</par>");
        logger.infoConsole("<par>|]=---------------------------------=[|</par>");
    }

    private void setupCommands() {
        logger.infoConsole("Registering commands");

        getCommand("cnv").setExecutor(commands);
        commands.registerCommand(new InfoCommand());
        commands.registerCommand(new TestsCommand());

        logger.infoConsole("All commands registered!");
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new ItemDespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemPackedEventHandler(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    private void setupHandlers() {
        if (configuration.getItemDespawnEventEnabled()) {
            itemDespawnEventHandler = new ItemDespawnEventHandler(this, configuration.getItemDespawnEventRunDelay());
        } else if (configuration.getItemDespawnEventWarningEnabled()) {
            logger.errorConsole("<par>CUSTOM_EVENTS.ITEM_DESPAWN_EVENT</par> is disabled!");
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
