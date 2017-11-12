package me.caneva20.CNVCore.Events.CustomEvents;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public final class ItemDespawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ItemStack despawnedItem;

    public ItemDespawnEvent(ItemStack despawnedItem) {
        this.despawnedItem = despawnedItem;
    }

    public ItemStack getItem () {
        return despawnedItem;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
