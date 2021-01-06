package com.hakan.worldborder;

import com.hakan.worldborder.listeners.BorderUpdateListeners;
import com.hakan.worldborder.utils.BorderVariables;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderPlugin extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    public static void setup(Plugin plugin) {
        if (instance != null) {
            Bukkit.getLogger().warning("WorldBorderAPI already registered.");
            return;
        }
        instance = plugin;
        Bukkit.getPluginManager().registerEvents(new BorderUpdateListeners(), plugin);
        BorderVariables.serverVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
    }

    @Override
    public void onEnable() {
        setup(this);
    }
}