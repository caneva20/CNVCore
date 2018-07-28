package me.caneva20.Core.Logger.Tags;

import me.caneva20.Core.Core;
import me.caneva20.Core.Logger.MessageLevel;

import java.util.HashSet;
import java.util.Set;

public class LoggerTags {
    private static Set<LoggerTag> tags = new HashSet<>();

    public static void setup(Core core) {
        setupDefaultTags();
    }

    private static void setupDefaultTags() {
        registerTag(new ParameterTag());
        registerTag(new BeginTag());
    }

    public static boolean registerTag(LoggerTag tag) {
        return tags.add(tag);
    }

    public static boolean replaceTag(LoggerTag tag) {
        if (tags.remove(tag)) {
            tags.add(tag);

            return true;
        }

        return false;
    }

    public static String process(String raw, MessageLevel level) {
        for (LoggerTag tag : tags) {
            raw = tag.cast(raw, level);
        }

        return raw;
    }
}
