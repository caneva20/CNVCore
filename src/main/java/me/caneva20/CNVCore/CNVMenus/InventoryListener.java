package me.caneva20.CNVCore.CNVMenus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    private void onInventoryClock (InventoryClickEvent e) {
        if (e.getAction() != InventoryAction.NOTHING && MenuBuilder.isMenu(e.getView().getTitle())) {
            e.setCancelled(true);

            if (e.getRawSlot() > e.getInventory().getSize()) {
                return;
            }

            MenuBuilder.triggerButton(e.getInventory().getName(), e.getSlot());
        }
    }
}
