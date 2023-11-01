package org.hmmbo.hide_server;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    Hide_Server main;
    public Commands(Hide_Server main, Hide_Server hideServer1) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage(main.getConfig().getString("Prefix") + " Please Use /hs Help To Know About Plugin");
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("setspawn")) {
                setLocation("setspawn", player.getLocation().getX(),  player.getLocation().getY(),  player.getLocation().getZ(),  player.getLocation().getYaw(),
                        player.getLocation().getPitch());
                player.sendMessage(main.getConfig().getString("Prefix") +" New Spawn Set Sucessfully");
            }
            if (args[0].equalsIgnoreCase("setwait")) {
                setLocation("setwait", player.getLocation().getX(),  player.getLocation().getY(),  player.getLocation().getZ(),  player.getLocation().getYaw(),
                        player.getLocation().getPitch());
                player.sendMessage(main.getConfig().getString("Prefix") +" New Wait Area Set Sucessfully");
            }
        }


        return false;
    }
    public void setLocation(String key, double x, double y, double z, float yaw, float pitch) {
        main.getConfig().set(key + ".x", x);
        main.getConfig().set(key + ".y", y);
        main.getConfig().set(key + ".z", z);
        main.getConfig().set(key + ".yaw", yaw);
        main.getConfig().set(key + ".pitch", pitch);
        main.saveConfig();
    }
}
