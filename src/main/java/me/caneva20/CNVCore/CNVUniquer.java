package me.caneva20.CNVCore;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class CNVUniquer {
    private long lastId;
    private CNVLogger logger;
    private CNVCore plugin;

    public CNVUniquer(CNVCore plugin) {
        this.plugin = plugin;

        logger = CNVCore.getMainLogger();
    }

    public ItemStack uniquefy (ItemStack itemStack) {
        if (isUniquefied(itemStack)) {
            return itemStack;
        }

        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();

        if (lore == null) {
            lore = new ArrayList<>();
        }

        lore.add("CNVUniquer>" + lastId++);

        meta.setLore(lore);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack deuniquefy (ItemStack itemStack) {
        if (!isUniquefied(itemStack)) {
            return itemStack;
        }

        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        List<String> newLore = new ArrayList<>();

        for (String loreLine : lore) {
            if (!loreLine.matches("^CNVUniquer>(\\d+)$")) {
                newLore.add(loreLine);
            }
        }

        meta.setLore(newLore);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public boolean isUniquefied (ItemStack itemStack) {
        List<String> lore = itemStack.getItemMeta().getLore();

        if (lore == null) {
            return false;
        }

        for (String loreLine : lore) {
            if (loreLine.matches("^CNVUniquer>(\\d+)$")) {
                return true;
            }
        }

        return false;
    }
}





















