package org.hmmbo.hide_server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class Hide_Server extends JavaPlugin implements Listener {

     static List<Player> waiting = new ArrayList<>();
     static Hide_Server main = null;
    @Override
    public void onEnable() {
        main = this;
        getConfig().options().copyDefaults();
        File qfile = new File(getDataFolder(), "config.yml");
        if (!qfile.exists()) {
            saveResource("config.yml", false);
        }
        Bukkit.getPluginManager().registerEvents(this,this);
        main.getLogger().info(
                "ooooooooo  oooooooooo       ooooo ooooo ooooo  oooo oooooooooo"+"\n"+
                        "888    888  888    888       888   888   888    88   888    888"+"\n"+
                        "888oooo88   888oooo88        888ooo888   888    88   888oooo88"+"\n"+
                      "888  88o    888              888   888   888    88   888    888"+"\n"+
                      "o888o  88o8 o888o            o888o o888o   888oo88   o888ooo888"+"\n"+
                      "                      A PLUGIN MADE BY HMMBO                     ");

        Objects.requireNonNull(getCommand("rphub")).setExecutor(new Commands(this, this));
        Objects.requireNonNull(getCommand("rphub")).setTabCompleter(new Commandtab());
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        waiting.add(e.getPlayer());
        e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), main.getConfig().getDouble("setwait.x"), main.getConfig().getDouble("setwait.y"),
                main.getConfig().getDouble("setwait.z"), main.getConfig().getInt("setwait.yaw"), main.getConfig().getInt("setwait.pitch")));
    }

    @EventHandler
    public static void Move(PlayerMoveEvent e) {
    if(waiting.contains(e.getPlayer())){
        e.setCancelled(true);
    }
    }
    @EventHandler
    public static void Moves(EntityDamageByEntityEvent e) {
        if(e.getEntityType().equals(EntityType.PLAYER))
        if(waiting.contains((Player) e.getEntity())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void Res_Pack(PlayerResourcePackStatusEvent e) {
        if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)){
            waiting.remove(e.getPlayer());
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), main.getConfig().getDouble("setspawn.x") ,main.getConfig().getDouble("setspawn.y"), main.getConfig().getDouble("setspawn.z"),
                    main.getConfig().getInt("setspawn.yaw"), main.getConfig().getInt("setspawn.pitch")));
        }
        if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED)){
            if(main.getConfig().getBoolean("kickondeny")){
                e.getPlayer().kickPlayer(main.getConfig().getString("kickmsg"));
            }
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
