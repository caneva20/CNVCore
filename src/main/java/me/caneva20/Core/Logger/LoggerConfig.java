package me.caneva20.Core.Logger;

import me.caneva20.Core.CNVConfig;
import me.caneva20.Core.Core;

import java.util.HashMap;
import java.util.Map;

public class LoggerConfig {
    private static CNVConfig config;
    private static Map<MessageLevel, ColorSet> colors = new HashMap<>();
    private static Map<MessageLevel, Boolean> enabledLevels = new HashMap<>();

    public static void setup(Core plugin) {
        config = new CNVConfig(plugin, "logger");

        loadColors();
        loadEnabledLevels();
    }

    private static void loadColors() {
        //Defaults
        setLevelColor(MessageLevel.SUCCESS, '2', 'a');
        setLevelColor(MessageLevel.ERROR, '4', 'c');
        setLevelColor(MessageLevel.INFO, '3', 'b');
        setLevelColor(MessageLevel.WARN, '6', 'e');
        setLevelColor(MessageLevel.DEBUG, '3', 'b');

        for (MessageLevel level : MessageLevel.values()) {
            try {
                char main = config.getString(level + ".COLORING.MAIN").charAt(0);
                char param = config.getString(level + ".COLORING.PARAM").charAt(0);

                setLevelColor(level, main, param);
            } catch (Exception ignore) {
            }
        }
    }

    private static void loadEnabledLevels() {
        for (MessageLevel level : MessageLevel.values()) {
            //Default value
            enabledLevels.put(level, true);

            try {
                boolean enabled = config.getBoolean(level + ".SHOW");

                enabledLevels.put(level, enabled);
            } catch (Exception ignored) {
            }
        }
    }

    private static void setLevelColor(MessageLevel level, char main, char parameter) {
        colors.put(level, new ColorSet(main, parameter));
    }

    public static ColorSet getColor(MessageLevel level) {
        return colors.get(level);
    }

    public static boolean isLevelEnabled(MessageLevel level) {
        return enabledLevels.get(level);
    }
}
