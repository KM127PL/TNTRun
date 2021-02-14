package eu.auracraft.tntrun;

import eu.auracraft.tntrun.commands.TNTRunCommand;
import eu.auracraft.tntrun.events.ChangeSettingsOnWorldChange;
import eu.auracraft.tntrun.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.Objects;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        Logger.setPrefix(this.getConfig().getString("plugin.prefix"));
        Logger.log("Starting TNTRun v" + this.getDescription().getVersion() + "...");
        Logger.log("Starting 1/2 - Registering commands.");
        registerCommands();
        Logger.log("Starting 2/3 - Registering events.");
        registerEvents();
        Logger.log("Starting 3/3 - Reloading configs.");
        this.saveDefaultConfig();
        this.reloadConfig();

        long end = System.currentTimeMillis();
        Logger.log("Started! (In " + (end - start) + "ms)"); // Calculate time spent starting up.
    }

    @Override
    public void onDisable() {
        Logger.log("Stopping...");
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("tnt")).setExecutor(new TNTRunCommand());
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new ChangeSettingsOnWorldChange(), this);
    }

    public static Plugin _getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin(Main.getPlugin(Main.class).getDescription().getName());
    }
}
