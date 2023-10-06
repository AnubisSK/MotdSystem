package sk.anubissvk.motdsystem.variables;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PremennePovolenia {
    public static Chat chat = null;

    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault"))
            return newString;
        if (newString.contains("{group}")) {
            if (!setupChat())
                return newString;
            newString = newString.replace("{group}", String.valueOf(chat.getPrimaryGroup(player)));
        }
        if (newString.contains("{userprefix}")) {
            if (!setupChat())
                return newString;
            newString = newString.replace("{userprefix}", String.valueOf(chat.getPlayerPrefix(player)));
        }
        if (newString.contains("{usersuffix}")) {
            if (!setupChat())
                return newString;
            newString = newString.replace("{usersuffix}", String.valueOf(chat.getPlayerSuffix(player)));
        }
        if (newString.contains("{prefix}")) {
            if (!setupChat())
                return newString;
            newString = newString.replace("{prefix}",
                    String.valueOf(chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player))));
        }
        if (newString.contains("{suffix}")) {
            if (!setupChat())
                return newString;
            newString = newString.replace("{suffix}",
                    String.valueOf(chat.getGroupSuffix(player.getWorld(), chat.getPrimaryGroup(player))));
        }
        return newString;
    }

    private static boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null)
            return false;
        chat = (Chat)rsp.getProvider();
        return (chat != null);
    }
}
