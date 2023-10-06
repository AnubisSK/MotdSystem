package sk.anubissvk.motdsystem.variables;

import sk.anubissvk.motdsystem.Motd;
import org.bukkit.entity.Player;

public class PremenneBukkit {
    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (newString.contains("{bukkitversion}"))
            newString = newString.replace("{bukkitversion}", Motd.getInstance().getServer().getBukkitVersion());
        if (newString.contains("{worldscount}"))
            newString = newString.replace("{worldscount}", String.valueOf(Motd.getInstance().getServer().getWorlds().size()));
        if (newString.contains("{servermotd}"))
            newString = newString.replace("{servermotd}", Motd.getInstance().getServer().getMotd());
        if (newString.contains("{onlineplayers}"))
            newString = newString.replace("{onlineplayers}", String.valueOf(Motd.getInstance().getServer().getOnlinePlayers().size()));
        if (newString.contains("{maxplayers}"))
            newString = newString.replace("{maxplayers}", String.valueOf(Motd.getInstance().getServer().getMaxPlayers()));
        if (newString.contains("{pluginscount}"))
            newString = newString.replace("{pluginscount}", String.valueOf((Motd.getInstance().getServer().getPluginManager().getPlugins()).length));
        return newString;
    }
}
