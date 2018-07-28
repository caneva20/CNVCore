package me.caneva20.Core.Util;

import me.caneva20.Core.CNVScheduler.CNVTime;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"SameParameterValue", "unused"})
public class Util {
    public static String convertSecondsToMinutes(int seconds) {
        int mins = 0;
        String formMins;
        String formSeconds;

        if (seconds >= 60) {
            mins = seconds / 60;
            seconds = seconds - (mins * 60);
        }

        if (seconds <= 9) {
            formSeconds = "0" + seconds;
        } else {
            formSeconds = seconds + "";
        }

        if (mins <= 9) {
            formMins = "0" + mins;
        } else {
            formMins = "" + mins;
        }

        return formMins + ":" + formSeconds;
    }

    public static String convertIntToDecimal(int value) {
        String decimal = "";

        switch (value) {
            case 1:
                decimal = "first";
                break;
            case 2:
                decimal = "second";
                break;
            case 3:
                decimal = "third";
                break;
            case 4:
                decimal = "fourth";
                break;
            case 5:
                decimal = "fifth";
                break;
            case 6:
                decimal = "sixth";
                break;
            case 7:
                decimal = "seventh";
                break;
            case 8:
                decimal = "eighth";
                break;
            case 9:
                decimal = "ninth";
                break;
        }

        return decimal;
    }

    public static String removeColor(String string) {
        return string.replaceAll("[§&](?:[0-9a-fA-F]|[lLKkrRMmNnOo])", "");
    }

    public static String convertToMinute (int seconds) {
        return convertSecondsToMinutes(seconds);
    }

    public static String capitalize(String string) {
        string = string.subSequence(0, 1).toString().toUpperCase() + string.subSequence(1, string.length()).toString().toLowerCase();
        return string;
    }

    public static String formatUsage(String usage) {
        usage = usage.replace("<", "&e<");
        usage = usage.replace(">", ">&6");
        usage = usage.replace("[", "&7[");
        usage = usage.replace("]", "]&6");

        return usage;
    }

    public static Block[] getBlocksAround (Location location, Material block, boolean onlySides) {
        List<Block> blocks = new ArrayList<>();

        Location[] locs = new Location[6];
        locs[0] = location.clone().add(-1, 0, 0);
        locs[1] = location.clone().add(1, 0, 0);
        locs[2] = location.clone().add(0, 0, -1);
        locs[3] = location.clone().add(0, 0, 1);
        locs[4] = location.clone().add(0, -1, 0);
        locs[5] = location.clone().add(0, 1, 0);

        if (block == null) {
            for (int i = 0; i < 4; i++) {
                blocks.add(locs[i].getBlock());
            }

            if (!onlySides) {
                for (int i = 4; i < 6; i++) {
                    blocks.add(locs[i].getBlock());
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (locs[i].getBlock().getType() == block) {
                    blocks.add(locs[i].getBlock());
                }
            }

            if (!onlySides) {
                for (int i = 4; i < 6; i++) {
                    if (locs[i].getBlock().getType() == block) {
                        blocks.add(locs[i].getBlock());
                    }
                }
            }
        }

        return blocks.toArray(new Block[0]);
    }

    public static Block getBlockBellow (Location location) {
        return location.clone().add(0, -1, 0).getBlock();
    }

    public static Block getBlockUp (Location location) {
        return location.clone().add(0, 1, 0).getBlock();
    }

    public static Block getBlockRight (Location location) {
        return location.clone().add(1, 0, 0).getBlock();
    }

    public static Block getBlockLeft (Location location) {
        return location.clone().add(-1, -1, 0).getBlock();
    }

    public static Block getBlockFront (Location location) {
        return location.clone().add(0, 0, 1).getBlock();
    }

    public static Block getBlockBack (Location location) {
        return location.clone().add(0, 0, -1).getBlock();
    }

    //
    public static Block getNthBlockBellow (Location location, int nth) {
        return location.clone().add(0, -nth, 0).getBlock();
    }

    public static Block getNthBlockUp (Location location, int nth) {
        return location.clone().add(0, nth, 0).getBlock();
    }

    public static Block getNthBlockRight (Location location, int nth) {
        return location.clone().add(nth, 0, 0).getBlock();
    }

    public static Block getNthBlockLeft (Location location, int nth) {
        return location.clone().add(-nth, -1, 0).getBlock();
    }

    public static Block getNthBlockFront (Location location, int nth) {
        return location.clone().add(0, 0, nth).getBlock();
    }

    public static Block getNthBlockBack (Location location, int nth) {
        return location.clone().add(0, 0, -nth).getBlock();
    }

    public static CNVTime convertToCNVTime (String time) {
        Pattern pattern = Pattern.compile("^(\\d{2}):(\\d{2})$");
        Matcher m = pattern.matcher(time);

        if (m.find()) {
            int hour = Integer.parseInt(m.group(1));
            int minute = Integer.parseInt(m.group(2));

            return new CNVTime(hour, minute);
        }

        return null;
    }

    public static int convertFromTicksToMinutes (long ticks) {
        return (int) (ticks / 20 / 60);
    }

    public static Integer[] getEmptySlots (Inventory inventory) {
        List<Integer> emptySlots = new ArrayList<>();

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                emptySlots.add(i);
            }
        }

        return emptySlots.toArray(new Integer[0]);
    }

    public static List<String> getParts (String in, int length) {
        List<String> parts = new ArrayList<>();

        int len = in.length();

        for (int i = 0; i < len; i += length) {
            parts.add(in.substring(i, Math.min(len, i + length)));
        }

        return parts;
    }

    public static String revertString (String in) {
        StringBuilder out = new StringBuilder();

        for (int i = in.length() - 1; i >= 0; i--) {
            out.append(in.charAt(i));
        }

        return out.toString();
    }

    public static String formatNumber(float money) {
        return formatNumber(String.format("%.000000f", money));
    }

    public static String formatNumber(double money) {
        return formatNumber(String.format("%.000000f", money));
    }

    public static String formatNumber(String money) {
        return formatNumber(money, 2);
    }

    /**
     * Formats a given amount of money following the rule bellow.
     * The string can contain ONLY zero or one .(dot) OR ,(comma), not more than one, not both.
     * Everything after the .(dot) or ,(comma) will be understood as cents, decimal currency and will not be formatted
     *
     * @param money The raw representation of money, without formatting
     * @return the money formatted if the given string matches the rule, otherwise the given string will be returned
     */
    public static String formatNumber(String money, int decimalPlaces) {
        if (!money.matches("^\\d+(([.,])\\d+)?$")) {
            return money;
        }

        StringBuilder cents = new StringBuilder();

        if (money.contains(".")) {
            String[] split = money.split("\\.");
            money = split[0];
            cents = new StringBuilder(split[1]);
        } else if (money.contains(",")) {
            String[] split = money.split(",");
            money = split[0];
            cents = new StringBuilder(split[1]);
        }

        String sign = "";

        if (money.contains("-")) {
            String[] split = money.split("-");
            money = split[1];
            sign = split[0];
        } else if (money.contains("+")) {
            String[] split = money.split("\\+");
            money = split[1];
            sign = split[0];
        }

        List<String> parts = getParts(revertString(money), 3);

        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < parts.size(); i++) {
            if (i == parts.size() -1) {
                formatted.append(parts.get(i));
            } else {
                formatted.append(parts.get(i)).append(",");
            }
        }

        if (cents.length() > decimalPlaces) {
            cents = new StringBuilder(cents.substring(0, decimalPlaces));
        } else if (cents.length() < decimalPlaces){
            for (int i = cents.length(); i < decimalPlaces; i++) {
                cents.append("0");
            }
        }

        return revertString(sign + formatted.toString()) + (!cents.toString().equals("") ? "." + cents : "");
    }

    public static String itemToString (Item item) {
        return "(" + item.getItemStack().getType() + "{" + item.getEntityId() + "}[x" + item.getItemStack().getAmount() + "])";
    }
}






















