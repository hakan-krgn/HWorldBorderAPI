package com.hakan.worldborder.listeners;

import com.hakan.worldborder.HWorldBorder;
import com.hakan.worldborder.WorldBorderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BorderUpdateListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                HWorldBorder hWorldBorder = WorldBorderAPI.getWorldBorder(player);
                if (hWorldBorder != null) {
                    hWorldBorder.send(player);
                }
            }
        }.runTaskLater(WorldBorderAPI.getInstance(), 3);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                HWorldBorder hWorldBorder = WorldBorderAPI.getWorldBorder(player);
                if (hWorldBorder != null) {
                    hWorldBorder.send(player);
                }
            }
        }.runTaskLater(WorldBorderAPI.getInstance(), 3);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                HWorldBorder hWorldBorder = WorldBorderAPI.getWorldBorder(player);
                if (hWorldBorder != null) {
                    hWorldBorder.send(player);
                }
            }
        }.runTaskLater(WorldBorderAPI.getInstance(), 3);
    }
}