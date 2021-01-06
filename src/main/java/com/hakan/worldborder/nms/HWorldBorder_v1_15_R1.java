package com.hakan.worldborder.nms;

import com.hakan.worldborder.HWorldBorder;
import com.hakan.worldborder.utils.BorderVariables;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_15_R1.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HWorldBorder_v1_15_R1 implements HWorldBorder {

    private final WorldBorder worldBorder;

    private int size;
    private int damageAmount;
    private int damageBuffer;
    private int warningDistance;
    private int warningTime;
    private List<String> players = new ArrayList<>();

    public HWorldBorder_v1_15_R1(Location location, int size, int damageAmount, int damageBuffer, int warningDistance, int warningTime) {
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.setCenter(location.getX(), location.getZ());
        worldBorder.setSize(size);
        worldBorder.setDamageAmount(damageAmount);
        worldBorder.setDamageBuffer(damageBuffer);
        worldBorder.setWarningDistance(warningDistance);
        worldBorder.setWarningTime(warningTime);
        worldBorder.world = ((CraftWorld) location.getWorld()).getHandle();

        this.worldBorder = worldBorder;
    }

    @Override
    public void send(Player player) {
        WorldBorder worldBorder = this.worldBorder;
        PacketPlayOutWorldBorder packetPlayOutWorldBorder = new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder);

        BorderVariables.playerHWorldBorder.put(player.getName(), this);
        this.players.add(player.getName());
    }

    @Override
    public void remove(Player player) {
        WorldBorder worldBorder = this.worldBorder;
        worldBorder.setCenter(0, 0);
        worldBorder.setSize(59999998);
        PacketPlayOutWorldBorder packetPlayOutWorldBorder = new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder);

        BorderVariables.playerHWorldBorder.remove(player.getName());
        this.players.clear();
    }

    @Override
    public void update() {
        WorldBorder worldBorder = this.worldBorder;
        PacketPlayOutWorldBorder packetPlayOutWorldBorder = new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        for (String playerName : getPlayers()) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder);
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        this.worldBorder.setSize(size);
    }

    @Override
    public int getDamageAmount() {
        return damageAmount;
    }

    @Override
    public void setDamageAmount(int damageAmount) {
        this.damageAmount = damageAmount;
        this.worldBorder.setDamageAmount(damageAmount);
    }

    @Override
    public int getDamageBuffer() {
        return damageBuffer;
    }

    @Override
    public void setDamageBuffer(int damageBuffer) {
        this.damageBuffer = damageBuffer;
        this.worldBorder.setDamageBuffer(damageBuffer);
    }

    @Override
    public int getWarningDistance() {
        return warningDistance;
    }

    @Override
    public void setWarningDistance(int warningDistance) {
        this.warningDistance = warningDistance;
        this.worldBorder.setWarningDistance(warningDistance);
    }

    @Override
    public int getWarningTime() {
        return warningTime;
    }

    @Override
    public void setWarningTime(int warningTime) {
        this.warningTime = warningTime;
        this.worldBorder.setWarningTime(warningTime);
    }

    @Override
    public List<String> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<String> players) {
        this.players = players;
    }
}