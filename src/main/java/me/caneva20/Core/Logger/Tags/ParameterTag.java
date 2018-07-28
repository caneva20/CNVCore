package me.caneva20.Core.Logger.Tags;

import me.caneva20.Core.Logger.ColorSet;
import me.caneva20.Core.Logger.LoggerConfig;
import me.caneva20.Core.Logger.MessageLevel;

public class ParameterTag extends LoggerTag {
    public ParameterTag() {
        super("par", false);
    }

    @Override
    protected String replacementBegin(MessageLevel level) {
        return "&" + getColor(level).parameter;
    }

    @Override
    protected String replacementEnd(MessageLevel level) {
        return "&" + getColor(level).main;
    }

    private ColorSet getColor(MessageLevel level) {
        return LoggerConfig.getColor(level);
    }
}
