package me.caneva20.Core.Events.CustomEvents;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class BatchItemDespawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final List<ItemStack> despawnedItems;

    public BatchItemDespawnEvent(List<ItemStack> despawnedItems) {
        this.despawnedItems = despawnedItems;
    }

    public List<ItemStack> getItems () {
        return despawnedItems;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
