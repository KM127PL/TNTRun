package eu.auracraft.tntrun.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public static void sendFormattedMessage(Player p, String msg) {
        ConfigUtils cu = new ConfigUtils();
        p.sendMessage(format(cu.getString("plugin.prefix") + " " + msg));
    }
}
