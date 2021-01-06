package com.hakan.worldborder.api;

import com.hakan.worldborder.HWorldBorder;
import com.hakan.worldborder.WorldBorderPlugin;
import com.hakan.worldborder.nms.*;
import com.hakan.worldborder.utils.BorderVariables;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldBorderAPI {

    public static void setup(Plugin plugin) {
        WorldBorderPlugin.setup(plugin);
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

        public HWorldBorder create() {
            switch (BorderVariables.serverVersion) {
                case "v1_8_R3":
                    return new HWorldBorder_v1_8_R3(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_9_R1":
                    return new HWorldBorder_v1_9_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_9_R2":
                    return new HWorldBorder_v1_9_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_10_R1":
                    return new HWorldBorder_v1_10_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_11_R1":
                    return new HWorldBorder_v1_11_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_12_R1":
                    return new HWorldBorder_v1_12_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_13_R1":
                    return new HWorldBorder_v1_13_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_13_R2":
                    return new HWorldBorder_v1_13_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_14_R1":
                    return new HWorldBorder_v1_14_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_15_R1":
                    return new HWorldBorder_v1_15_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_16_R1":
                    return new HWorldBorder_v1_16_R1(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_16_R2":
                    return new HWorldBorder_v1_16_R2(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
                case "v1_16_R3":
                    return new HWorldBorder_v1_16_R3(this.center, this.size, this.damageAmount, this.damageBuffer, this.warningDistance, this.warningTime);
            }
            return null;
        }
    }
}