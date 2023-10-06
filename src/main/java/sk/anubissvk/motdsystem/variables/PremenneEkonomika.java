package sk.anubissvk.motdsystem.variables;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
public class PremenneEkonomika {
    public static Economy econ = null;

    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault"))
            return newString;
        if (newString.contains("{money}")) {
            if (!setupEconomy())
                return newString;
            newString = newString.replace("{money}", String.valueOf(econ.format(econ.getBalance((OfflinePlayer)player))));
        }
        return newString;
    }

    private static boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        econ = (Economy)rsp.getProvider();
        return (econ != null);
    }
}

