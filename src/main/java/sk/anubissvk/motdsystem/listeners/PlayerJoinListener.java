package sk.anubissvk.motdsystem.listeners;

import sk.anubissvk.motdsystem.Motd;
import sk.anubissvk.motdsystem.utils.ChatUtil;
import sk.anubissvk.motdsystem.variables.Premenne;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;
public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (Motd.getInstance().getConfig().getBoolean("Pridat-sa-do-hry-MOTD.Enabled"))
            for (String s1 : Motd.getInstance().getConfig().getStringList("Pridat-sa-do-hry-MOTD.Spravy"))
                p.sendMessage(ChatUtil.format(Premenne.replaceVariables(s1, p)));
    }
}
