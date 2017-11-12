package me.caneva20.CNVCore.CNVMenus;

import me.caneva20.CNVCore.CNVConfig;
import me.caneva20.CNVCore.Util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBuilder {
    //Singleton
    private static Map<JavaPlugin, MenuBuilder> instances = new HashMap<>();

    public static MenuBuilder get(JavaPlugin plugin) {
        if (!instances.containsKey(plugin)) {
            instances.put(plugin, new MenuBuilder(plugin));
        }

        return instances.get(plugin);
    }
    //End singleton

    private static Map<String, Menu> createdMenus = new HashMap<>();
    private CNVConfig c;

    //Static
    public static boolean isMenu (String menuName) {
        menuName = ColorUtil.removeColor(menuName);

        return createdMenus.containsKey(menuName);
    }

    //Non static
    private MenuBuilder(JavaPlugin plugin) {
        c = new CNVConfig(plugin, "Menus");
    }

    public void disable() {}

    public void reload() {
        c.reloadCustomConfig();
    }

    public Menu createMenu(String menuName, int rows) {
        if (!hasMenu(menuName)) {
            return null;
        }

        //It'll get a value between 0 and 6
        rows = Math.max(1, Math.min(6, rows));

        //Used for multi pages menus, work stopped for now
//        int invCount = rows / 5;
//
//        if (rows % 5 == 1) {
//            invCount--;
//        }

        String displayName = c.getString("MENUS." + menuName + ".NAME");

        Inventory inv = Bukkit.createInventory(null, rows * 9, ColorUtil.translate(displayName));

        ItemStack blank = createBlank(menuName);

        for (int i = 0; i < rows * 9; i++) {
            inv.setItem(i, blank);
        }

        for (String button : c.getSection("MENUS." + menuName + ".BUTTONS")) {
            String path = "MENUS." + menuName + ".BUTTONS." + button + ".";

            int slot = c.getInt(path + ".SLOT");

            inv.setItem(slot, createButton(menuName, button));
        }

        Menu menu = new Menu(inv);
        createdMenus.put(ColorUtil.removeColor(displayName), menu);

        return menu;
    }

    public Menu createEmptyMenu (String name, int rows) {
        rows = Math.max(1, Math.min(6, rows));

        Inventory inv = Bukkit.createInventory(null, rows * 9, ColorUtil.translate(name));

        Menu menu = new Menu(inv);
        createdMenus.put(ColorUtil.removeColor(name), menu);

        return menu;
    }

    public Menu createEmptyMenu (String name, ItemStack blankItem, int rows) {
        Menu menu = createEmptyMenu(name, rows);

        Inventory inv = menu.getInventory();

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, blankItem);
        }

        menu.setInventory(inv);

        return menu;
    }

    public ItemStack createItem (String rawId, String name, List<String> lore) {
        int id;
        short data;

        String regex = "(\\d+):(\\d+)";
        if (rawId.matches(regex)) {
            id = Integer.parseInt(rawId.replaceAll(regex, "$1"));
            data = Short.parseShort(rawId.replaceAll(regex, "$2"));
        } else {
            id = Integer.parseInt(rawId.replaceAll("\\D*", ""));
            data = 0;
        }

        ItemStack item = new ItemStack(id, 1, data);

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return null;
        }

        meta.setDisplayName(ColorUtil.translate(name));

        if (lore.size() != 0) {
            meta.setLore(ColorUtil.translate(lore));
        }

        item.setItemMeta(meta);

        return item;
    }

    /**
     * @param menuName The name of the menu
     * @return true if its find a valid menu with the name of @menuName
     */
    public boolean hasMenu(String menuName) {
        for (String menus : c.getSection("MENUS")) {
            if (menus.equals(menuName)) {
                return c.getSection("MENUS." + menuName + ".BUTTONS").size() != 0;
            }
        }

        return false;
    }

    private ItemStack createItem(String path) {
        path = path + ".";
        String name = c.getString(path + "BUTTON_NAME");
        List<String> lore = c.getStringList(path + "BUTTON_LORE");

        String rawId = c.getString(path + "ITEM_ID");

        return createItem(rawId, name, lore);
    }

    private ItemStack createButton (String menuName, String buttonName) {
        return createItem("MENUS." + menuName + ".BUTTONS." + buttonName);
    }

    private ItemStack createBlank(String menuName) {
        return createItem("MENUS." + menuName + ".BLANK_SLOT");
    }

    public static boolean triggerButton (String menu, int id) {
        menu = ColorUtil.removeColor(menu);

        return isMenu(menu) && createdMenus.get(menu).triggerButton(id);
    }
}
