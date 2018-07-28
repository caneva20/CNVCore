package me.caneva20.Core.CNVActions.Actions;

import me.caneva20.Core.CNVActions.Action;
import me.caneva20.Core.CNVActions.Paramenters.ActionInteger;
import me.caneva20.Core.CNVActions.Paramenters.ActionPlayer;
import me.caneva20.Core.CNVActions.Paramenters.ActionString;
import me.caneva20.Core.CNVActions.Paramenters.ActionStringList;
import me.caneva20.Core.Core;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemAction extends Action {
    private static final String PLAYER = "PLAYER";
    private static final String ID = "ID";
    private static final String DATA = "DATA";
    private static final String AMOUNT = "AMOUNT";
    private static final String NAME = "NAME";
    private static final String LORE = "LORE";
    private static final String ENCHANTMENT = "ENCHANTMENT";

    public ItemAction() {
        super("ITEM");

        addParameter(PLAYER, new ActionPlayer(true));
        addParameter(ID, new ActionInteger(true, 0));
        addParameter(DATA, new ActionInteger(0));
        addParameter(AMOUNT, new ActionInteger(1));
        addParameter(NAME, new ActionString());
        addParameter(LORE, new ActionStringList());
        addParameter(ENCHANTMENT, new ActionStringList());
    }

    @Override
    protected void process() {
        Player player = get(PLAYER);
        int id = get(ID);
        int data = get(DATA);
        int amount = get(AMOUNT);
        String name = get(NAME);
        String[] lore = get(LORE);
        String[] enchantments = get(ENCHANTMENT);

        ItemStack item = new ItemStack(id, amount, (short) data);

        for (int i = 0; i < lore.length; i++) {
            lore[i] = lore[i]
                    .replace("{{PLAYER_NAME}}", player.getName())
                    .replace("{{AMOUNT}}", amount + "")
                    .replace("{{DATA}}", data + "")
                    .replace("{{ID}}", id + "")
                    .replace("{{TYPE_NAME}}", item.getType().toString());

            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        name = name
                .replace("{{PLAYER_NAME}}", player.getName())
                .replace("{{AMOUNT}}", amount + "")
                .replace("{{DATA}}", data + "")
                .replace("{{ID}}", id + "")
                .replace("{{TYPE_NAME}}", item.getType().toString());

        name = ChatColor.translateAlternateColorCodes('&', name);

        ItemMeta meta = item.getItemMeta();

        if (!"".equals(name)) {
            meta.setDisplayName(name);
        }

        meta.setLore(Arrays.asList(lore));

        for (String enchantment : enchantments) {
            meta = enchant(enchantment, meta);
        }

        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }

    @Override
    protected boolean validate() {
        return get(PLAYER) != null && ((int) get(ID)) != 0;
    }

    @Override
    protected void getValidation() {
        if (get(PLAYER) == null) {
            validation(PLAYER, false, "Player is null");
        }

        if (((int) get(ID)) == 0) {
            validation(ID, false, "ID is 0");
        }
    }

    private ItemMeta enchant(String enchant, ItemMeta meta) {
        if (enchant.matches("^\\w+:\\d+$")) {
            String enchantName = enchant.replaceAll("^(\\w+):\\d+$", "$1");
            int enchantLevel = 1;
            try {
                enchantLevel = Integer.parseInt(enchant.replaceAll("^\\w+:(\\d+)$", "$1"));
            } catch (NumberFormatException ignored) {
            }

            Enchantment enchantment = Enchantment.getByName(enchantName);

            if (enchantment != null) {
                meta.addEnchant(enchantment, enchantLevel, true);

                return meta;
            }

            Core.getMainLogger().errorConsole("Enchantment <par>" + enchantName + "</par> not found");
        } else if (enchant.matches("^\\w+$")) {
            Enchantment enchantment = Enchantment.getByName(enchant);

            if (enchantment != null) {
                meta.addEnchant(enchantment, 1, true);

                return meta;
            }

            Core.getMainLogger().errorConsole("Enchantment <par>" + enchant + "</par> not found");
        }

        return meta;
    }
}



















