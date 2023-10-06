package sk.anubissvk.motdsystem.listeners;

import sk.anubissvk.motdsystem.Motd;
import sk.anubissvk.motdsystem.configuration.SpravcaSuborov;
import sk.anubissvk.motdsystem.utils.ChatUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerPing(ServerListPingEvent event) {
        String motd1 = SpravcaSuborov.getConfigFile().getString("Server-MOTD.Linka-1");
        String motd2 = SpravcaSuborov.getConfigFile().getString("Server-MOTD.Linka-2");
        motd1 = ChatUtil.serverMOTDColorFormat(motd1);
        motd2 = ChatUtil.serverMOTDColorFormat(motd2);
        event.setMotd(String.valueOf(motd1) + "\n§r" + motd2);
        if (SpravcaSuborov.getConfigFile().get("Server-Maximalny-hraci.Upravit") != null &&
                SpravcaSuborov.getConfigFile().getBoolean("Server-Maximalny-hraci.Upravit"))
            event.setMaxPlayers(SpravcaSuborov.getConfigFile().getInt("Server-Maximalny-hraci.Maximalny-pocet-hracov"));
        if (SpravcaSuborov.getConfigFile().getBoolean("Vlastna-ikona-servera.Povolene"))
            try {
                event.setServerIcon(Bukkit.getServer().loadServerIcon(serverIcon()));
            } catch (UnsupportedOperationException e) {
                SpravcaSuborov.getConfigFile().set("Vlastna-ikona-servera.Povolene", Boolean.valueOf(false));
                System.out.println("[MOTD] Funkcia vlastnej ikony servera bola zakázaná z dôvodu chyby.");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public static BufferedImage serverIcon() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(Motd.getInstance().getDataFolder(),
                    "/serverikona/" + SpravcaSuborov.getConfigFile().getString("Vlastna-ikona-servera.Obrazok")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }
}
