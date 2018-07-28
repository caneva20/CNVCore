package me.caneva20.Core;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Vault {
    private Economy economy;
    private Permission permission;
    private JavaPlugin plugin;

    public Vault(JavaPlugin plugin) {
        this.plugin = plugin;
        setupEconomy();
        setupPermission();
    }

    private Boolean setupEconomy() {
        RegisteredServiceProvider economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);

        if (economyProvider != null) {
            economy = ((Economy) economyProvider.getProvider());
        }

        return economy != null;
    }

    private Boolean setupPermission() {
        RegisteredServiceProvider permissionProvider = plugin.getServer().getServicesManager().getRegistration(Permission.class);

        if (permissionProvider != null) {
            permission = ((Permission) permissionProvider.getProvider());
        }

        return permission != null;
    }

    public void withdrawPlayer(Player player, double amount) {
        economy.withdrawPlayer(player, amount);
    }

    public void depositPlayer(Player player, double amount) {
        economy.depositPlayer(player, amount);
    }

    public boolean hasEnoughMoney(Player player, double amount) {
        return economy.has(player, amount);
    }

    public double getPlayerMoney(Player player) {
        return economy.getBalance(player);
    }

    public boolean hasAccount(Player player) {
        return economy.hasAccount(player);
    }

    public void createPlayerAccount(Player player) {
        economy.createPlayerAccount(player);
    }

    public String formatEconomy(double amount) {
        return economy.format(amount);
    }

    public boolean playerIsInGroup(Player player, String group) {
        return permission.playerInGroup(player, group);
    }

    public void setPlayerGroup(Player player, String group) {
        permission.playerAddGroup(player, group);
    }

    public String getMainPlayerGroup(Player player) {
        String[] playerGroups = permission.getPlayerGroups(player);

        if (playerGroups != null) {
            return playerGroups[playerGroups.length - 1];
        }

        return null;
    }

    public String getGroup(Player player, int group) {
        return permission.getPlayerGroups(player)[group];
    }

    public int getAmountOfGroups(Player player) {
        return permission.getPlayerGroups(player).length;
    }

    public void removeFromGroup(Player player, String group) {
        permission.playerRemoveGroup(player, group);
    }

    public void registerPermission(String permission) {
        if (permission == null || "null".equals(permission) || "".equals(permission)) {
            return;
        }

        for (org.bukkit.permissions.Permission perms : plugin.getServer().getPluginManager().getPermissions()) {
            if (perms.getName().equalsIgnoreCase(permission)) {
                return;
            }
        }

        plugin.getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission(permission));
    }

    public void addPermission(Player player, String permission) {
        this.permission.playerAdd(player, permission);
    }

    public void removePermission(Player player, String permission) {
        this.permission.playerRemove(player, permission);
    }
}