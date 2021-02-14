package eu.auracraft.tntrun.utils;

import eu.auracraft.tntrun.Main;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {
    private static Configuration config;

    public ConfigUtils() {
        this.config = Main.getPlugin(Main.class).getConfig();
    }

    public Configuration getConfig() {
        return this.config;
    }

    public String getString(String path) {
        return this.getConfig().getString(path);
    }

    public double getDouble(String path) {
        return this.getConfig().getDouble(path);
    }

    public Object get(String path) {
        return this.getConfig().get(path);
    }
}
