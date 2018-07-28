package me.caneva20.Core.Logger.Tags;

import me.caneva20.Core.Logger.ColorSet;
import me.caneva20.Core.Logger.LoggerConfig;
import me.caneva20.Core.Logger.MessageLevel;

public class BeginTag extends LoggerTag {
    public BeginTag() {
        super("begin", true);
    }

    @Override
    protected String replacement(MessageLevel level) {
        return "&" + getColor(level).main;
    }

    private ColorSet getColor(MessageLevel level) {
        return LoggerConfig.getColor(level);
    }
}
