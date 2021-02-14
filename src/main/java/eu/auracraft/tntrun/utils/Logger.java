package eu.auracraft.tntrun.utils;

import eu.auracraft.tntrun.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    private static String prefix = Main.getPlugin(Main.class).getName();

    public static void setPrefix(String prefix) {
        Logger.prefix = prefix;
    }

    public static void log(String msg) {
        msg = ChatColor.translateAlternateColorCodes('&', prefix + " " + msg);
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public static void debug(String msg) {
        log("&7[&eDEBUG&7]&r" + msg);
    }
}