package sk.anubissvk.motdsystem.configuration;

import java.util.Arrays;

public class Konfiguracnespravy {
    private static Vlastnakonfiguracia config = SpravcaSuborov.getConfigFile();

    public static void loadDefaultConfigMessages() {
        if (config.get("Server-MOTD") == null) {
            addConfigMessage("Server-MOTD", "", new String[] { "Toto je motd servera." });
            addConfigMessage("Server-MOTD.Linka-1", "&5&lEND&f&lCRAFT &7- &cDeveloper Server &f1.19.4", new String[0]);
            addConfigMessage("Server-MOTD.Linka-2", "&aNovinky &7> Nový system", new String[0]);
        }
        if (config.get("Pridat-sa-do-hry-MOTD") == null) {
            addConfigMessage("Pridat-sa-do-hry-MOTD", "", new String[] { "Motd hráča, keď sa pripojí." });
            addConfigMessage("Pridat-sa-do-hry-MOTD.Povolene", Boolean.valueOf(true), new String[0]);
            addConfigMessage("Pridat-sa-do-hry-MOTD.Spravy", Arrays.asList(new String[] { "&eVitajte na serveri." } ), new String[] { "Skontrolujte, či fórum neobsahuje zástupné symboly.", "Podporované pre zástupné symboly PlaceholderAPI." });
        }
        if (config.get("Vlastna-ikona-servera") == null) {
            addConfigMessage("Vlastna-ikona-servera", "", new String[] { "Ikona servera." });
            addConfigMessage("Vlastna-ikona-servera.Povolene", Boolean.valueOf(false), new String[] { "Nastavenie na true načíta obrázok z priečinka motd." });
            addConfigMessage("Vlastna-ikona-servera.Obrazok", "server-icon.png", new String[] { "Uistite sa, že je to 64 x 64 pixelov vo formáte png." });
        }
        if (config.get("Server-Maximalny-hraci") == null) {
            addConfigMessage("Server-Maximalny-hraci", "", new String[] { "The maximum players of the server." });
            addConfigMessage("Server-Maximalny-hraci.Upravit", Boolean.valueOf(true), new String[0]);
            addConfigMessage("Server-Maximalny-hraci.Maximalny-pocet-hracov", Integer.valueOf(200), new String[0]);
        }
        if (config.get("Max-Players") != null)
            config.set("Max-Players", null);
    }

    private static void addConfigMessage(String path, Object arg1, String... comments) {
        config.addDefault(path, arg1, comments);
    }
}
