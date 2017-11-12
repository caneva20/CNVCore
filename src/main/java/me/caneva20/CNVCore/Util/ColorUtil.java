package me.caneva20.CNVCore.Util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ColorUtil {
    public static String translate (char alternate, String in) {
        return ChatColor.translateAlternateColorCodes(alternate, in);
    }

    public static String translate (String in) {
        return translate('&', in);
    }

    public static List<String> translate (char alternate, List<String> in) {
        List<String> out = new ArrayList<>();

        for (String s : in) {
            out.add(translate(alternate, s));
        }

        return out;
    }

    public static List<String> translate (List<String> in) {
        return translate('&', in);
    }

    public static String strip (String in) {
        return ChatColor.stripColor(in);
    }

    public static List<String> strip (List<String> in) {
        List<String> out = new ArrayList<>();

        for (String s : in) {
            out.add(strip(s));
        }

        return out;
    }

    public static String clear (String in) {
        return Util.removeColor(in);
    }

    public static List<String> clear (List<String> in) {
        List<String> out = new ArrayList<>();

        for (String s : in) {
            out.add(clear(s));
        }

        return out;
    }

    public static String removeColor (String in) {
        return clear(in);
    }

    public static List<String> removeColor (List<String> in) {
        return clear(in);
    }
}














