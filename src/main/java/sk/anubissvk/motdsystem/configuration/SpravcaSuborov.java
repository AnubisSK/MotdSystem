package sk.anubissvk.motdsystem.configuration;

import sk.anubissvk.motdsystem.Motd;
import java.io.File;
import java.util.List;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SpravcaSuborov {
    public File file = null;

    public FileConfiguration fileConfiguration;

    private SpravcaSuborov(String fileName, String fileType) {
        if (!Motd.getInstance().getDataFolder().exists())
            Motd.getInstance().getDataFolder().mkdir();
        File serverIcon = new File(Motd.getInstance().getDataFolder(), "/server icon");
        if (!serverIcon.exists())
            serverIcon.mkdirs();
        this.file = new File(Motd.getInstance().getDataFolder(), String.valueOf(fileName) + "." + fileType);
        this.fileConfiguration = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    }

    public static Vlastnakonfiguracia getConfigFile() {
        return Motd.getInstance().getConfig();
    }

    public void set(String path, Object value) {
        createFile();
        if (path == null)
            return;
        this.fileConfiguration.set(path, value);
        try {
            createFile();
            this.fileConfiguration.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDefault(String path, Object value) {
        createFile();
        if (path == null)
            return;
        if (!this.fileConfiguration.contains(path))
            set(path, value);
    }

    public boolean contains(String value) {
        createFile();
        return this.fileConfiguration.contains(value);
    }

    public <T> T get(String path) {
        createFile();
        return (T)this.fileConfiguration.get(path);
    }

    public Object get(String path, Object value) {
        createFile();
        return this.fileConfiguration.get(path, value);
    }

    public FileConfiguration getFile() {
        createFile();
        return this.fileConfiguration;
    }

    public String getString(String path) {
        createFile();
        return this.fileConfiguration.getString(path);
    }

    public List<String> getStringList(String path) {
        createFile();
        return this.fileConfiguration.getStringList(path);
    }

    public int getInt(String path) {
        createFile();
        return this.fileConfiguration.getInt(path);
    }

    public boolean getBoolean(String path) {
        createFile();
        return this.fileConfiguration.getBoolean(path);
    }

    public double getDouble(String path) {
        createFile();
        return this.fileConfiguration.getDouble(path);
    }

    public Set<String> getKeys(boolean arg0) {
        createFile();
        return this.fileConfiguration.getKeys(arg0);
    }

    public ConfigurationSection getConfigurationSection(String arg0) {
        createFile();
        return this.fileConfiguration.getConfigurationSection(arg0);
    }

    public ConfigurationSection createSection(String arg0) {
        createFile();
        ConfigurationSection section = this.fileConfiguration.createSection(arg0);
        save();
        return section;
    }

    public void save() {
        try {
            createFile();
            this.fileConfiguration.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            createFile();
            this.fileConfiguration = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        if (!this.file.exists())
            try {
                this.file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void deleteFile() {
        if (this.file.exists())
            try {
                this.file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
