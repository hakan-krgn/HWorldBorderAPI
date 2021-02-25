package com.hakan.worldborder.nms;

import com.hakan.worldborder.HBorderColor;
import com.hakan.worldborder.HWorldBorder;
import com.hakan.worldborder.utils.BorderVariables;
import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import net.minecraft.server.v1_13_R2.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HWorldBorder_v1_13_R2 implements HWorldBorder {

    private final WorldBorder worldBorder;

    private HBorderColor color;
    private List<String> players = new ArrayList<>();

    public HWorldBorder_v1_13_R2(Location location, int size, int damageAmount, int damageBuffer, int warningDistance, int warningTime, HBorderColor color) {
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.setCenter(location.getX(), location.getZ());
        worldBorder.setSize(size);
        worldBorder.setDamageAmount(damageAmount);
        worldBorder.setDamageBuffer(damageBuffer);
        worldBorder.setWarningDistance(warningDistance);
        worldBorder.setWarningTime(warningTime);
        worldBorder.world = ((CraftWorld) location.getWorld()).getHandle();

        this.color = color;
        this.worldBorder = worldBorder;

        update();
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
        switch (this.color) {
            case RED:
                this.worldBorder.transitionSizeBetween(getSize(), getSize() - 0.1D, Long.MAX_VALUE);
                break;
            case GREEN:
                this.worldBorder.transitionSizeBetween(getSize(), getSize() + 0.1D, Long.MAX_VALUE);
                break;
            case BLUE:
                this.worldBorder.transitionSizeBetween(getSize(), getSize(), Long.MAX_VALUE);
                break;
        }
        WorldBorder worldBorder = this.worldBorder;
        PacketPlayOutWorldBorder packetPlayOutWorldBorder = new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        for (String playerName : getPlayers()) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
                playerConnection.sendPacket(packetPlayOutWorldBorder);
            }
        }
    }

    @Override
    public HBorderColor getColor() {
        return this.color;
    }

    @Override
    public void setColor(HBorderColor hBorderColor) {
        this.color = hBorderColor;
    }

    @Override
    public double getSize() {
        return this.worldBorder.getSize();
    }

    @Override
    public void setSize(double size) {
        this.worldBorder.setSize(size);
    }

    @Override
    public double getDamageAmount() {
        return this.worldBorder.getDamageAmount();
    }

    @Override
    public void setDamageAmount(double damageAmount) {
        this.worldBorder.setDamageAmount(damageAmount);
    }

    @Override
    public double getDamageBuffer() {
        return this.worldBorder.getDamageBuffer();
    }

    @Override
    public void setDamageBuffer(double damageBuffer) {
        this.worldBorder.setDamageBuffer(damageBuffer);
    }

    @Override
    public int getWarningDistance() {
        return this.worldBorder.getWarningDistance();
    }

    @Override
    public void setWarningDistance(int warningDistance) {
        this.worldBorder.setWarningDistance(warningDistance);
    }

    @Override
    public int getWarningTime() {
        return this.worldBorder.getWarningTime();
    }

    @Override
    public void setWarningTime(int warningTime) {
        this.worldBorder.setWarningTime(warningTime);
    }

    @Override
    public List<String> getPlayers() {
        return this.players;
    }

    @Override
    public void setPlayers(List<String> players) {
        this.players = players;
    }
}