package me.caneva20.CNVCore;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
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
        economy.withdrawPlayer(player.getName(), amount);
    }

    public void withdrawPlayer(String playerName, double amount) {
        economy.withdrawPlayer(playerName, amount);
    }

    public void depositPlayer(Player player, double amount) {
        economy.depositPlayer(player.getName(), amount);
    }

    public void depositPlayer(String playerName, double amount) {
        economy.depositPlayer(playerName, amount);
    }

    public boolean hasEnoughMoney(Player player, double amount) {
        return economy.has(player.getName(), amount);
    }

    public boolean hasEnoughMoney(String name, double amount) {
        return economy.has(name, amount);
    }

    public double getPlayerMoney (String name) {
        return economy.getBalance(name);
    }

    public double getPlayerMoney (Player player) {
        return economy.getBalance(player.getName());
    }

    public boolean hasAccount(Player player) {
        return economy.hasAccount(player.getName());
    }

    public boolean hasAccount(String name) {
        return economy.hasAccount(name);
    }

    public void createPlayerAccount(Player player) {
        economy.createPlayerAccount(player.getName());
    }

    public void createPlayerAccount(String name) {
        economy.createPlayerAccount(name);
    }

    public String formatEconomy(double amount) {
        return economy.format(amount);
    }

    public boolean playerIsInGroup(Player player, String group) {
        return permission.playerInGroup(player, group);
    }

    public boolean playerIsInGroup(String name, String group, World world) {
        return permission.playerInGroup(world, name, group);
    }

    public void setPlayerGroup(Player player, String group) {
        permission.playerAddGroup(player, group);
    }

    public void setPlayerGroup(String name, String group, World world) {
        permission.playerAddGroup(world, name, group);
    }

    public String getMainPlayerGroup(Player player) {
        String[] playerGroups = permission.getPlayerGroups(player);

        if (playerGroups != null) {
            return playerGroups[playerGroups.length - 1];
        }

        return null;
    }

    public String getMainPlayerGroup(String name, World world) {
        String[] playerGroups = permission.getPlayerGroups(world, name);

        if (playerGroups != null) {
            return playerGroups[playerGroups.length - 1];
        }

        return null;
    }

    public String getGroup(Player player, int group) {
        return permission.getPlayerGroups(player)[group];
    }

    public String getGroup(String name, int group, World world) {
        return permission.getPlayerGroups(world, name)[group];
    }

    public int getAmountOfGroups(Player player) {
        return permission.getPlayerGroups(player).length;
    }

    public int getAmountOfGroups(String name, World world) {
        return permission.getPlayerGroups(world, name).length;
    }

    public void removeFromGroup(Player player, String group) {
        permission.playerRemoveGroup(player, group);
    }

    public void removeFromGroup(String name, String group, World world) {
        permission.playerRemoveGroup(world, name, group);
    }

    public void registerPermission (String permission) {
        if (permission == null) {return;}

        if (permission.equals("null")) {return;}

        if (permission.isEmpty()) {return;}

        for (org.bukkit.permissions.Permission perms : plugin.getServer().getPluginManager().getPermissions()) {
            if (perms.getName().equalsIgnoreCase(permission)) {
                return;
            }
        }

        plugin.getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission(permission));
    }

    public void addPermission (Player player, String permission) {
        this.permission.playerAdd(player, permission);
    }

    public void removePermission (Player player, String permission) {
        this.permission.playerRemove(player, permission);
    }

    /** Deprecated
     * Use registerPermission instead
     * @param permNode
     */
    @SuppressWarnings("JavaDoc")
    @Deprecated
    public void addPerm(String permNode) {
        if (permNode == null) {return;}

        if (permNode.equals("null")) {return;}

        if (permNode.isEmpty()) {return;}

        for (org.bukkit.permissions.Permission perms : plugin.getServer().getPluginManager().getPermissions()) {
            if (perms.getName().equalsIgnoreCase(permNode)) {
                return;
            }
        }

        plugin.getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission(permNode));
    }
}