package eu.auracraft.tntrun.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeSettingsOnWorldChange implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Bukkit.broadcastMessage(e.getFrom().getName() + " -> " + e.getPlayer().getWorld().getName());
    }

}
