package com.hakan.worldborder;

import com.hakan.worldborder.listeners.BorderUpdateListeners;
import com.hakan.worldborder.nms.*;
import com.hakan.worldborder.utils.BorderVariables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldBorderAPI {

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

    public static HWorldBorder getWorldBorder(String playerName) {
        return BorderVariables.playerHWorldBorder.get(playerName);
    }

    public static HWorldBorder getWorldBorder(Player player) {
        return getWorldBorder(player.getName());
    }

    public static WorldBorderManager getWorldBorderManager() {
        return new WorldBorderManager();
    }

    public static class WorldBorderManager {

        private Location center;
        private int size = 16;
        private int damageAmount = 0;
        private int damageBuffer = 0;
        private int warningDistance = 0;
        private int warningTime = 0;
        private HBorderColor color = HBorderColor.BLUE;

        private WorldBorderManager() {
        }

        public WorldBorderManager setCenter(Location center) {
            this.center = center;
            return this;
        }

        public WorldBorderManager setSize(int size) {
            this.size = size;
            return this;
        }

        public WorldBorderManager setDamageAmount(int damageAmount) {
            this.damageAmount = damageAmount;
            return this;
        }

        public WorldBorderManager setDamageBuffer(int damageBuffer) {
            this.damageBuffer = damageBuffer;
            return this;
        }

        public WorldBorderManager setWarningDistance(int warningDistance) {
            this.warningDistance = warningDistance;
            return this;
        }

        public WorldBorderManager setWarningTime(int warningTime) {
            this.warningTime = warningTime;
            return this;
        }

        public WorldBorderManager setColor(HBorderColor color) {
            this.color = color;
            return this;
        }

        public HWorldBorder create() {
            switch (BorderVariables.serverVersion) {
                case "v1_8_R3":
                    return new HWorldBorder_v1_8_R3(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_9_R1":
                    return new HWorldBorder_v1_9_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_9_R2":
                    return new HWorldBorder_v1_9_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_10_R1":
                    return new HWorldBorder_v1_10_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_11_R1":
                    return new HWorldBorder_v1_11_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_12_R1":
                    return new HWorldBorder_v1_12_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_13_R1":
                    return new HWorldBorder_v1_13_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_13_R2":
                    return new HWorldBorder_v1_13_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_14_R1":
                    return new HWorldBorder_v1_14_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_15_R1":
                    return new HWorldBorder_v1_15_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_16_R1":
                    return new HWorldBorder_v1_16_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_16_R2":
                    return new HWorldBorder_v1_16_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
                case "v1_16_R3":
                    return new HWorldBorder_v1_16_R3(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime, this.color);
            }
            return null;
        }
    }
}