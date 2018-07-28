package me.caneva20.Core.Events.CustomEvents;

import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("unused")
public class ItemPackedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Item itemRemoved;
    private Item itemAdded;

    public ItemPackedEvent(Item itemRemoved, Item itemAdded) {
        this.itemRemoved = itemRemoved;
        this.itemAdded = itemAdded;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Item getItemRemoved() {
        return itemRemoved;
    }

    public Item getItemAdded() {
        return itemAdded;
    }
}
