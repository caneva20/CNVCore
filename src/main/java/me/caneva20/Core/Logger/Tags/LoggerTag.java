package me.caneva20.Core.Logger.Tags;

import me.caneva20.Core.Logger.MessageLevel;

public abstract class LoggerTag {
    protected final MessageLevel defaultLevel = MessageLevel.INFO;
    private String name;
    private boolean selfContained;

    public LoggerTag(String name, boolean selfContained) {
        this.name = name;
        this.selfContained = selfContained;
    }

    public LoggerTag(String name) {
        this(name, true);
    }

    public String cast(String raw, MessageLevel level) {
        raw = raw.replace("<" + name + ">", replacementBegin(level));

        if (selfContained) {
            raw = raw.replace("<" + name + "/>", replacementBegin(level));
        } else {
            raw = raw.replace("</" + name + ">", replacementEnd(level));
        }

        return raw;
    }

    protected String replacement(MessageLevel level) {
        return "";
    }

    protected String replacementBegin(MessageLevel level) {
        return replacement(level);
    }

    protected String replacementEnd(MessageLevel level) {
        return "";
    }
}
