package sk.anubissvk.motdsystem.variables;

import sk.anubissvk.motdsystem.Motd;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
public class Premenne {
    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (Motd.placeholderApi)
            newString = PlaceholderAPI.setPlaceholders(player, newString);
        if (!newString.contains("{") || !newString.contains("}"))
            return newString;
        newString = PremenneHrac.replaceVariables(newString, player);
        newString = PremenneBukkit.replaceVariables(newString, player);
        newString = PremenneEkonomika.replaceVariables(newString, player);
        newString = PremennePovolenia.replaceVariables(newString, player);
        if (newString.contains("{world_"))
            newString = SvetovePremenne.replaceVariables(newString, player);
        return newString;
    }
}
