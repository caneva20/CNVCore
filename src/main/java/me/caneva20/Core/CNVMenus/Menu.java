package me.caneva20.Core.CNVMenus;

import me.caneva20.Core.Generics.Actions.A0.Action;
import me.caneva20.Core.Util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Inventory inventory;
    private Map<Integer, Action> buttons = new HashMap<>();

    public Menu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void open (Player player) {
        player.openInventory(inventory);
    }

    public void close (Player player) {
        String openName = ColorUtil.removeColor(player.getOpenInventory().getTitle());
        String inventoryName = ColorUtil.removeColor(inventory.getName());

        if (openName.equals(inventoryName)) {
            player.closeInventory();
        }
    }

    public void addButton (int id, Action action) {
        buttons.put(id, action);
    }

    public void addButton (ItemStack button, int id, Action action) {
        inventory.setItem(id, button);
        addButton(id, action);
    }

    public void highlightButton (int id) {
        ItemStack item = inventory.getItem(id);

        if (item.getType() != Material.AIR) {
            item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        }
    }

    public void unhighlightButton(int id) {
        ItemStack item = inventory.getItem(id);

        if (item.getType() != Material.AIR) {
            item.removeEnchantment(Enchantment.ARROW_INFINITE);
        }
    }

    public void highlightAllButtons () {
        for (int i = 0; i < inventory.getSize(); i++) {
            highlightButton(i);
        }
    }

    public void unhighlightAllButtons () {
        for (int i = 0; i < inventory.getSize(); i++) {
            unhighlightButton(i);
        }
    }

    public boolean isHighlighted (int id) {
        ItemStack item = inventory.getItem(id);

        if (item.getType() != Material.AIR) {
            return item.containsEnchantment(Enchantment.ARROW_INFINITE);
        }

        return false;
    }

    public boolean triggerButton (int id) {
        if (buttons.containsKey(id)) {
            buttons.get(id).run();
            return true;
        }

        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
