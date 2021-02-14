package eu.auracraft.tntrun.commands;

import eu.auracraft.tntrun.Main;
import eu.auracraft.tntrun.utils.*;
import eu.auracraft.tntrun.world.TNTWorldCreator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TNTRunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ConfigUtils cu = new ConfigUtils();
            if(player.hasPermission("auracraft.tntrun")) {
                if(args.length == 0) {
                    Message.sendFormattedMessage(player, "&cThis command was not found!");
                } else {

                    switch(args[0]) {
                        case "tp":
                            if(TNTWorldCreator.doesWorldExist(new ConfigUtils().getString("plugin.world.name"))) {
                                Message.sendFormattedMessage(player, "&aTeleporting you to &7" + new ConfigUtils().getString("plugin.world.name"));
                                player.teleport(new Location(TNTWorldCreator.getWorld(new ConfigUtils().getString("plugin.world.name")), 0, 60, 0));
                                return true;
                            }
                            Message.sendFormattedMessage(player, "&cWorld &7" + new ConfigUtils().getString("plugin.world.name") + "&c does not exist!");
                        break;
                        case "create":
                            Message.sendFormattedMessage(player, "&aCreating TNTRun world of name " + new ConfigUtils().getString("plugin.world.name"));
                            TNTWorldCreator.createWorld(cu.getString("plugin.world.name"));
                            TNTWorldCreator.setBorder(cu.getString("plugin.world.name"), cu.getDouble("plugin.world.size"));
                            TNTWorldCreator.prepareWorld(cu.getString("plugin.world.name"));
                            Message.sendFormattedMessage(player, "&aWorld sucessfully created!");
                        break;
                        case "delete":
                            TNTWorldCreator.deleteWorld(new ConfigUtils().getString("plugin.world.name"));
                            Message.sendFormattedMessage(player, "&aWorld sucessfully deleted!");
                            // do stuff
                        case "recreate":
                            // do stuff
                        break;
                        default:
                            return true;
                    }

                }
                return true;
            }
            player.sendMessage(Message.format("&c(!) Nie masz permisji do tej komendy!"));
        }
        return false;
    }

}
