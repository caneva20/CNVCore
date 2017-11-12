package me.caneva20.CNVCore.Events;

import me.caneva20.CNVCore.CNVCore;
import me.caneva20.CNVCore.Events.CustomEvents.ItemPackedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemPackedEventHandler implements Listener {
    private static final int range = 5;
    private CNVCore plugin;

    public ItemPackedEventHandler(CNVCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onItemSpawn (ItemSpawnEvent e) {
        List<Entity> nearbyEntities = e.getEntity().getNearbyEntities(range, range, range);
        List<Item> nearbyItems = new ArrayList<>();
        List<Item> itemsPacked = new ArrayList<>();

        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity instanceof Item && !nearbyEntity.isDead()) {
                nearbyItems.add(((Item) nearbyEntity));
            }
        }

        for (Item curItem : nearbyItems) {
            ItemStack curItemStack = curItem.getItemStack();
            ItemStack itemStack = e.getEntity().getItemStack();

            if (itemStack.isSimilar(curItemStack)) {
                int neededToMax = itemStack.getMaxStackSize() - itemStack.getAmount();

                if (neededToMax > 0) {
                    if (curItemStack.getAmount() > neededToMax) {
                        curItemStack.setAmount(curItemStack.getAmount() - neededToMax);
                        itemStack.setAmount(itemStack.getAmount() + neededToMax);

                        curItem.setItemStack(curItemStack);
                    } else {
                        itemStack.setAmount(itemStack.getAmount() + curItemStack.getAmount());
                        //DONT DO THIS!! For some reason it will work wrongly
//                        curItemStack.setAmount(0);

                        itemsPacked.add(curItem);

                        curItem.remove();
                    }

                    e.getEntity().setItemStack(itemStack);
                }
            }
        }

        for (Item item : itemsPacked) {
            plugin.getServer().getPluginManager().callEvent(new ItemPackedEvent(item, e.getEntity()));
        }
    }
}
