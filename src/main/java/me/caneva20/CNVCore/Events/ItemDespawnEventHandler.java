package me.caneva20.CNVCore.Events;

import me.caneva20.CNVCore.CNVCore;
import me.caneva20.CNVCore.Events.CustomEvents.BatchItemDespawnEvent;
import me.caneva20.CNVCore.Events.CustomEvents.ItemDespawnEvent;
import me.caneva20.CNVCore.Events.CustomEvents.ItemPackedEvent;
import me.caneva20.CNVCore.Util.Util;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ItemDespawnEventHandler implements Listener {
    private CNVCore plugin;

    private List<Item> spawnedItems = new ArrayList<>();
    private List<Item> itemsToRemove = new ArrayList<>();

    public ItemDespawnEventHandler(CNVCore plugin, long runDelay) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        run(runDelay);
    }

    private void run (long runDelay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                List<Item> newItems = new ArrayList<>();

                for (World world : plugin.getServer().getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof Item) {
                            Item item = (Item)entity;

                            newItems.add(item);
                        }
                    }
                }

                for (Item item : new ArrayList<>(spawnedItems)) {
                    if (item != null && !hasItem(newItems, item) && !hasItem(itemsToRemove, item)) {
                        //Item removed
                        itemsToRemove.add(item);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, runDelay);

        new BukkitRunnable() {
            @Override
            public void run() {
                List<ItemStack> batchItems = new ArrayList<>();

                for (Item item : new ArrayList<>(itemsToRemove)) {
                    removeItem(item);
                    batchItems.add(item.getItemStack());
                    plugin.getServer().getPluginManager().callEvent(new ItemDespawnEvent(item.getItemStack()));
                }

                itemsToRemove.clear();
                plugin.getServer().getPluginManager().callEvent(new BatchItemDespawnEvent(batchItems));
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void addItem (Item item) {
        if (!hasItem(item)) {
            item.setItemStack(item.getItemStack());
            spawnedItems.add(item);
        }
    }

    private boolean removeItem (Item item) {
        boolean removedFromSpawned = false;
        boolean removedFromToRemove = false;

        if (hasItem(item)) {
            spawnedItems.remove(item);

            removedFromSpawned = true;
        }

        if (hasItem(itemsToRemove, item)) {
            itemsToRemove.remove(item);

            removedFromToRemove = true;
        }

        if (!removedFromSpawned && !removedFromToRemove) {
            CNVCore.getMainLogger().warnConsole("Item <par>" + Util.itemToString(item) + "</par> could not be removed. It was not in any list");

            return false;
        } else {
            return true;
        }
    }

    private boolean hasItem (Item other) {
        return hasItem(spawnedItems, other);
    }

    private boolean hasItem (List<Item> itemList, Item other) {
        for (Item item : itemList) {
            if (item.getEntityId() == other.getEntityId()) {
                return true;
            }
        }

        return false;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onItemPickup (PlayerPickupItemEvent e) {
        Item item = e.getItem();

        //Dont remember what it does
//        item.setItemStack(item.getItemStack());

        removeItem(item);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    private void onItemSpawn (ItemSpawnEvent e) {

        Item item = e.getEntity();

        addItem(item);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onItemPacked (ItemPackedEvent e) {
        removeItem(e.getItemRemoved());
    }

    public void disable() {
        spawnedItems = new ArrayList<>();
    }
}























