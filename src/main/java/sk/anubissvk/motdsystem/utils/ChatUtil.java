package sk.anubissvk.motdsystem.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String format(String arg0) {
        return ChatColor.translateAlternateColorCodes('&', arg0);
    }

    public static String stripColor(String arg0) {
        String newString = arg0;
        newString = stripColorFormat(newString);
        return ChatColor.stripColor(newString);
    }

    public static String stripPlaceHolderColor(String placeHolder) {
        return stripColor(placeHolder);
    }
    private static String stripColorFormat(String string) {
        string = string.replace("&0", "");
        string = string.replace("&1", "");
        string = string.replace("&2", "");
        string = string.replace("&3", "");
        string = string.replace("&4", "");
        string = string.replace("&5", "");
        string = string.replace("&6", "");
        string = string.replace("&7", "");
        string = string.replace("&8", "");
        string = string.replace("&9", "");
        string = string.replace("&a", "");
        string = string.replace("&b", "");
        string = string.replace("&c", "");
        string = string.replace("&d", "");
        string = string.replace("&e", "");
        string = string.replace("&f", "");
        string = string.replace("&k", "");
        string = string.replace("&l", "");
        string = string.replace("&m", "");
        string = string.replace("&n", "");
        string = string.replace("&o", "");
        string = string.replace("&r", "");
        return string;
    }
    public static String serverMOTDColorFormat(String string) {
        string = string.replace("&0", "§0");
        string = string.replace("&1", "§1");
        string = string.replace("&2", "§2");
        string = string.replace("&3", "§3");
        string = string.replace("&4", "§4");
        string = string.replace("&5", "§5");
        string = string.replace("&6", "§6");
        string = string.replace("&7", "§7");
        string = string.replace("&8", "§8");
        string = string.replace("&9", "§9");
        string = string.replace("&a", "§a");
        string = string.replace("&b", "§b");
        string = string.replace("&c", "§c");
        string = string.replace("&d", "§d");
        string = string.replace("&e", "§e");
        string = string.replace("&f", "§f");
        string = string.replace("&k", "§k");
        string = string.replace("&l", "§l");
        string = string.replace("&m", "§m");
        string = string.replace("&n", "§n");
        string = string.replace("&o", "§o");
        string = string.replace("&r", "§r");
        return string;
    }
}
