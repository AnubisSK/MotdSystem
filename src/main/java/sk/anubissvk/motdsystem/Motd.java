package sk.anubissvk.motdsystem;

import sk.anubissvk.motdsystem.commands.Manazerprikazov;
import sk.anubissvk.motdsystem.configuration.Konfiguracnespravy;
import sk.anubissvk.motdsystem.configuration.Vlastnakonfiguracia;
import sk.anubissvk.motdsystem.listeners.PlayerJoinListener;
import sk.anubissvk.motdsystem.listeners.ServerListPingListener;
import sk.anubissvk.motdsystem.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Motd extends JavaPlugin {
    private static Motd motd;
    private Vlastnakonfiguracia config;
    private File file;

    public static boolean placeholderApi;


    public void onEnable() {
        motd = this;
        setupConfig();
        createServerIconFile();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            System.out.println("[MOTDSYSTEM] [Placeholder] PlaceholderAPI je pripojený.");
            placeholderApi = true;
        }
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new PlayerJoinListener(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new ServerListPingListener(), (Plugin)this);
        getCommand("motd").setExecutor((CommandExecutor)new Manazerprikazov());
        getCommand("nastavservermotd").setExecutor((CommandExecutor)new Manazerprikazov());
    }
    public void onDisable() {
        motd = null;
    }
    private void setupConfig() {
        this.file = new File(getDataFolder(), "nastavenie.yml");
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            FileUtil.copy(getResource("nastavenie.yml"), this.file);
        }
        this.config = Vlastnakonfiguracia.loadConfiguration(this.file);
        Konfiguracnespravy.loadDefaultConfigMessages();
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        this.config = Vlastnakonfiguracia.loadConfiguration(this.file);
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createServerIconFile() {
        File folder = new File(String.valueOf(getDataFolder().getPath()) + "/serverikona/");
        if (!folder.exists() || (folder.listFiles()).length <= 0)
            saveResource("serverikona/server-icon.png", true);
    }

    public File getFile() {
        return this.file;
    }

    public Vlastnakonfiguracia getConfig() {
        return this.config;
    }

    public static Motd getInstance() {
        return motd;
    }
}
